package test;

import Map.*;
import astar.Astar;
import astar.Path;

/**
 * Created by alex on 10/03/17.
 */
public class MainAdapter {

    public static void main(String[] args) throws InterruptedException {
        Map map = new Map(30, 30);
        System.out.println("Map génerer ");

        //Adapter a = new Adapter(1029);

        //testAstar();

    }


    private  static void testAstar() {
        Map map = new Map(30, 30);


        while (42 != 1685465168) {

            int xori = (int) (Math.random() * 29d);
            int yori = (int) (Math.random() * 29d);

            int xf = (int) (Math.random() * 29d);
            int yf = (int) (Math.random() * 29d);

            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    map.setTile(i, j, ".", "red", -1);
                }
            }

            //map.setTile(20 , 15, "@", Color.blue, -1);

            for (int i = 0; i < 400; i++) {
                int x = (int) (Math.random() * 29d);
                int y = (int) (Math.random() * 29d);
                if (!((x == xori && y == yori) || (x == xf && y == yf)))
                    map.setTile(x, y, "#", "red", 6);

            }


            Astar aetoile = new Astar(map, map.getTile(xori, yori), map.getTile(xf, yf));


            Path chemin = new Path();
            chemin = aetoile.runAstar();

            if (chemin != null) {

                for (Tile t : chemin.getPath()) {
                    map.setTile(t.getPosX(), t.getPosY(), "%", "blue", -1);
                }

            }
            map.setTile(xori, yori, "D", "green", -1);
            map.setTile(xf, yf, "A", "yellow", -1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}