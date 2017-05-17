package adapter;

import map.Map;
import map.Tuple;
import units.Action;
import units.Enemy;
import units.Master;
import utils.Log;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Adapter extends Thread {

    private Socket socket;
    private int port;
    private BufferedWriter writer;
    private BufferedReader reader;
    private ListenSocket ls;
    private Master master;

    // new tiles seen by the unit, reset when we see it
    private ArrayList<Tuple<Integer, Integer>> tmpNewTiles;
    // pos enemy, if seen by the last unit else set to -1 -1
    private Tuple<Integer, Integer> posEnemy;

    private int last_id_unit = 1;

    //private int nb_action = 0;

    //parameters width=xx height=yy

    private void initSocket() {
        try {
            socket = new Socket(InetAddress.getByName("localhost"), port);
            Log.d("Connexion Etablie !");
            reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private int extractX(String[] tab_line) {
        String[] buf = tab_line[1].split("=");
        return Integer.parseInt(buf[1]);
    }

    private int extractY(String[] tab_line) {
        String[] buf = tab_line[2].split("=");
        return Integer.parseInt(buf[1]);
    }

    private void processLine(String line) {
        String[] tab_line = line.split(" ");
        int x = extractX(tab_line);
        int y = extractY(tab_line);
        switch (tab_line[0]) {
            case "parameters":
                // x-> height, y -> width
                Log.d("width " + x + " height " + y);
                master.setMap(new Map(y, x));
                Controller.getInstance().initGUI(y, x);
                break;
            case "print":
                updateMap(tab_line, x, y);
                // on ajoute a la cell a la liste des cell vue par cette unité

                if(tab_line[3].equals("ally") ) {
                    // c'est un ally on met a jour ca map de case vue
                    last_id_unit = Integer.parseInt(tab_line[4].split("=")[1]);
                    int hp = Integer.parseInt(tab_line[5].split("=")[1]);
                    char symbole = tab_line[6].split("=")[1].charAt(0);
                    master.updateEntity(last_id_unit, hp, x, y,symbole);

                } else if (tab_line[3].equals("ennemy")) {
                    // sur une condition, quand les joueurs on finit de jouer il faudrait récupérer le dernier
                    // move du joueur qui est envoyé après
                    // on met à jour seulement les IA qui peuvent le voir bouger
                    if(Controller.getInstance().isIA()) {

                        for(java.util.Map.Entry<Integer, Enemy> entry : master.getListUnits().entrySet()) {
                            Integer key = entry.getKey();
                            Enemy value = entry.getValue();

                            if (master.IACanSeeEnemy(key, new Tuple<>(x, y))) {
                                System.out.println("[DEBUG] On met à jour la position de l'unité " + key + " (" + x + ", " + y + ")");
                                master.setPosEnemyByUnit(key, new Tuple<>(x, y));
                            }
                        }

                        /*for (int i = 1; i <= master.getNbIA(); i++) {
                            System.out.println(master.getNbIA());
                            if (master.IACanSeeEnemy(i, new Tuple<>(x, y))) {
                                //System.out.println("[DEBUG] On met à jour la position de l'unité " + i + " (" + x + ", " + y + ")");
                                Log.d("On met à jour la position de l'unité " + i + " (" + x + ", " + y + ")");
                                master.setPosEnemyByUnit(i, new Tuple<>(x, y));
                            }
                        }*/
                    }
                } else {
                    // sinon c'est un terrain decouvert par l'unit
                    master.addNewTilesByUnit(last_id_unit, new Tuple<>(x, y));
                }
                break;
            case "action?":
                if (!Controller.getInstance().isIA()) {
                    // we change the controller status to allow the user input (action)
                    Controller.getInstance().setStatus(Controller.Status.ACTION);
                    Controller.getInstance().getMapGUI().setFocus(x, y);
                } else {
                    Controller.getInstance().getMapGUI().setFocus(x, y);
                    int id = Integer.parseInt(tab_line[3].split("=")[1]);
                    sendIAAction(master.playUnit(id), new Tuple<>(x, y), id);
                }

                // we need to see for which unit we need to move and stuff
                break;
            case "hide":
                master.hide(x, y);
                break;
        }
    }

    private void updateMap(String[] tab_line, int x, int y) {
        // the type : "terrain", "ally", "enemy"
        String type_terrain =  tab_line[3];

        if (tab_line[3].equals("ally") || tab_line[3].equals("ennemy")) {
            String[] buf = tab_line[6].split("=");
            // we update the map
            master.updateMap(x, y, type_terrain, buf[1]);
        } else {
            // the type , number of the type : mountain, water, food ...
            String[] buf = (tab_line[3].equals("food") ? tab_line[5].split("=") : tab_line[4].split("="));

            // we update the map
            master.updateMap(x, y, type_terrain, buf[1]);
        }
    }

    public void sendIAAction(Action actionIA, Tuple<Integer, Integer> posIA, int id) {

        Tuple<Integer, Integer> move = new Tuple<>(actionIA.getX(), actionIA.getY());
        String action = "";



        Log.coordonate("Pos_IA", posIA.x, posIA.y);
        Log.coordonate("Move_IA", move.x, move.y);

        if (actionIA.getAction() == Action.ActionType.Move) {
            master.updateMapOccupied(posIA.x, posIA.y, false);
            master.updateMapOccupied(move.x, move.y, true);
            if (move.y - posIA.y == -1) {
                // north
                Log.move("north");
                action = "north\n";
            } else if (move.y - posIA.y == 1) {
                // south
                Log.move("south");
                action = "south\n";
            } else if (move.x - posIA.x == -1) {
                // east
                Log.move("west");
                action = "west\n";
            } else if (move.x - posIA.x == 1) {
                // west
                Log.move("east");
                action = "east\n";
            }
            else {
                Log.move("Ne fait rien");
            }

            // when the IA MOVED
            master.Communicate(id);

        } else if (actionIA.getAction() == Action.ActionType.Attack) {
            if (move.y - posIA.y == -1) {
                // north
                Log.move("Attack UP");
                action = "attack 0 -1\n";
            } else if (move.y - posIA.y == 1) {
                // south
                Log.move("Attack DOWN");
                action = "attack 0 1\n";
            } else if (move.x - posIA.x == -1) {
                // east
                Log.move("Attack LEFT");
                action = "attack -1 0\n";
            } else if (move.x - posIA.x == 1) {
                // west
                Log.move("Attack RIGHT");
                action = "attack 1 0\n";
            }
            else {
                Log.move("Ne fait rien");
            }
        }


        try {
            if(action != "") {
                writer.write(action);
                writer.flush();
                Controller.getInstance().getMapGUI().setFocus(-1, -1);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void sendAction(String key) {
        Log.move("Key entered : " + key);
        String msg = "";
        switch (key) {
            case "Z":
                msg = "north\n";
                break;
            case "D":
                msg = "east\n";
                break;
            case "Q":
                msg = "west\n";
                break;
            case "S":
                msg = "south\n";
                break;
            case "NumPad-8":
            case "U":
                // ATTACK UP
                msg = "attack 0 -1\n";
                break;
            case "NumPad-6":
            case "K":
                //ATTACK RIGHT
                msg = "attack 1 0\n";
                break;
            case "NumPad-2":
            case "M":
                // ATTACK DOWN
                msg = "attack 0 1\n";
                break;
            case "NumPad-4":
            case "H":
                // ATTACK  LEFT
                msg = "attack -1 0\n";
                break;
        }
        try {
            if (!msg.equals("") && Controller.getInstance().getStatus() == Controller.Status.ACTION) {
                writer.write(msg);
                writer.flush();
                Controller.getInstance().setStatus(Controller.Status.WAIT);
                Log.move(msg);
                Controller.getInstance().getMapGUI().setFocus(-1, -1);
            } else {
                Log.move("Error of input or not the right time to move...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Adapter(int port, Master master) {
        this.port = port;
        this.master = master;
        this.tmpNewTiles = new ArrayList<>();
        this.posEnemy = new Tuple<>(-1,-1);
        initSocket();
        Log.d("Création de la socket de lecture");
        ls = new ListenSocket(reader);
        Log.d("Socket de lecture créé");
        this.start();
        Log.d("Thread de récupération de ligne lancé");
    }

    public void run() {
        String line;
        while(true) {
            line = ls.getLastLine();
            processLine(line);
            Log.line(line);
        }
    }
}
