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
    }
}
