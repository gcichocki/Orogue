package adapter;

import Map.Map;
import Map.Terrain;
import Map.Character;
import units.Master;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Adapter extends Thread {

    private Socket socket;
    private int port;
    private BufferedWriter writer;
    private BufferedReader reader;
    private ListenSocket ls;
    private Master master;

    //parameters width=xx height=yy

    private void initSocket() {
        try {
            socket = new Socket(InetAddress.getByName("localhost"), port);
            System.out.println("Connection Etablie !");
            reader =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int extractY(String[] tab_line) {
        String[] buf = tab_line[1].split("=");
        return Integer.parseInt(buf[1]);
    }

    private int extractX(String[] tab_line) {
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
                System.out.println("taille de la map : " + x + " * " + y);
                master.setMap(new Map(y, x));
                break;
            case "print":
                updateMap(tab_line, x, y);
                break;
            case "action?":
                // we change the controller status to allow the user input (action)
                Controller.getInstance().setStatus(Controller.Status.ACTION);
                // we need to see for which unit we need to move and stuff
                //TODO
                break;
        }
    }

    private void updateMap(String[] tab_line, int x, int y) {
        if(tab_line[3].equals("terrain")) {
            String[] buf = tab_line[4].split("=");
            int type = Integer.parseInt(buf[1]);
            master.updateMap(x, y, Terrain.terrain_sym.get(type), Terrain.terrain_color.get(type), type);
        }
        if (tab_line[3].equals("ally")) {
            String[] buf = tab_line[6].split("=");
            master.updateMap(x, y, Character.character_to_sym.get(buf[1]), Character.character_to_color.get(buf[1]), -1);
        }
        if (tab_line[3].equals("ennemy")) {
            String[] buf = tab_line[6].split("=");
            master.updateMap(x, y, Character.character_to_sym.get(buf[1]), Character.character_to_color.get(buf[1]), -1);
        }
    }

    public void sendAction(String key) {
        System.out.println("Key entered : " + key);
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
            case "U":
                msg = "attack 0 -1\n";
                break;
            case "K":
                msg = "attack 1 0\n";
                break;
            case "M":
                msg = "attack 0 1\n";
                break;
            case "H":
                msg = "attack -1 0\n";
                break;
        }
        try {
            if (!msg.equals("") && Controller.getInstance().getStatus() == Controller.Status.ACTION) {
                writer.write(msg + "\n");
                writer.flush();
                Controller.getInstance().setStatus(Controller.Status.WAIT);
                System.out.println(msg);
            } else {
                System.out.println("Error of input or not the right time to move...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* private void updateAction() {

    }*/

    public Adapter(int port, Master master) {
        this.port = port;
        this.master = master;
        initSocket();
        System.out.println("Création de la socket de lecture");
        ls = new ListenSocket(reader);
        System.out.println("Socket de lecture crée");
        this.start();
        System.out.println("Thread de récupération de ligne lancé");
    }

    public void run() {
        String line;
        //int cpt = 0;
        while(true) {
            line = ls.getLastLine();
            System.out.println(line);
            processLine(line);
            //cpt++;
            //System.out.println(cpt);
        }
    }
}
