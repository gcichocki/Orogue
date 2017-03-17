package adapter;

import Map.Map;

import java.awt.*;

/**
 * Created by alex on 10/03/17.
 */
public class MainAdapter {

    public static void main(String[] args) {
        Map map = new Map(250, 180);
        System.out.println("Map génerer ");

        Adapter a = new Adapter(1029);
        //map.setTile(0 , 0, "@", Color.blue);
        //map.setTile(20 -1, 15, "#", Color.RED);
    }
}
