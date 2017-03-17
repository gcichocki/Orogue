package adapter;

import Map.Map;
import Map.Terrain;
import Map.Character;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Adapter extends Thread {

    private Socket socket;
    private int port;
    private BufferedWriter writer;
    private BufferedReader reader;
    private ListenSocket ls;

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
        if(tab_line[0].equals("print")) {
            updateMap(tab_line, x, y);
        }
    }

    private void updateMap(String[] tab_line, int x, int y) {
        if(tab_line[3].equals("terrain")) {
            String[] buf = tab_line[4].split("=");
            int type = Integer.parseInt(buf[1]);
            Map.setTile(x, y, Terrain.terrain_sym.get(type), Terrain.terrain_color.get(type));
        }
        if (tab_line[3].equals("ally")) {
            String[] buf = tab_line[6].split("=");
            if (buf[1].equals("h")) {
                Map.setTile(x, y, Character.character_to_sym.get("h"), Character.character_to_color.get("h"));
            }

        }
    }

   /* private void updateAction() {

    }*/

    public Adapter(int port) {
        this.port = port;
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
