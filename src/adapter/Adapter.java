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
                if(tab_line[3].equals("ally") ) {
                    int id = Integer.parseInt(tab_line[4].split("=")[1]);
                    int hp = Integer.parseInt(tab_line[5].split("=")[1]);
                    char symbole = tab_line[6].split("=")[1].charAt(0);
                    master.updateEntity(id, hp, x, y,symbole);
                }
                break;
        }
    }

    private void updateMap(String[] tab_line, int x, int y) {
        if(tab_line[3].equals("terrain")) {
            String[] buf = tab_line[4].split("=");
            int type = Integer.parseInt(buf[1]);
            //Map.setTile(x, y, Terrain.terrain_sym.get(type), Terrain.terrain_color.get(type), type);
            master.updateMap(x, y, Terrain.terrain_sym.get(type), Terrain.terrain_color.get(type), type);
        }
        if (tab_line[3].equals("ally")) {
            String[] buf = tab_line[6].split("=");
            if (buf[1].equals("h")) {
                //Map.setTile(x, y, Character.character_to_sym.get("h"), Character.character_to_color.get("h"), -1);
                master.updateMap(x, y, Character.character_to_sym.get("h"), Character.character_to_color.get("h"), -1);
            }

        }
        if (tab_line[3].equals("ennemy")) {
            String[] buf = tab_line[6].split("=");
            if (buf[1].equals("@")) {
                //Map.setTile(x, y, Character.character_to_sym.get("@"), Character.character_to_color.get("@"), -1);
                master.updateMap(x, y, Character.character_to_sym.get("@"), Character.character_to_color.get("@"), -1);
            }

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
