package adapter;

import Map.Map;
import Map.Tile;
import Map.Terrain;

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
            socket = new Socket(InetAddress.getLocalHost(), port);
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
    }

   /* private void updateAction() {

    }*/

    public Adapter(int port) {
        this.port = port;
        initSocket();
        ls = new ListenSocket(reader);
    }

    public void run() {
        String line;
        while(true) {
            line = ls.getLastLine();
            processLine(line);
        }
    }
}
