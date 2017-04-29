package test;

import map.*;
import astar.Astar;
import astar.Path;

/**
 * Created by alex on 10/03/17.
 */
public class MainAdapter {

    public static void main(String[] args) throws InterruptedException {
        Map map = new Map(30, 30);
        System.out.println("map génerer ");

        //Adapter a = new Adapter(1029);

        testAstar();

        //coucou

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
                    map.updateTile(i, j, "terrain", "2");
                }
            }

            //map.setTile(20 , 15, "@", Color.blue, -1);

            for (int i = 0; i < 400; i++) {
                int x = (int) (Math.random() * 29d);
                int y = (int) (Math.random() * 29d);
                if (!((x == xori && y == yori) || (x == xf && y == yf)))
                    map.updateTile(x, y, "terrain", "6");

            }


            Astar aetoile = new Astar(map, map.getTile(xori, yori), map.getTile(xf, yf));


            Path chemin = new Path();
            chemin = aetoile.runAstar();

            if (chemin != null) {

                for (Tile t : chemin.getPath()) {
                    map.updateTile(t.getPosX(), t.getPosY(), "terrain", "8");
                    //map.setTile(t.getPosX(), t.getPosY(), "%", "blue", -1);
                }

            }
            map.updateTile(xori, yori, "terrain", "3");
            //map.setTile(xori, yori, "D", "green", -1);
            map.updateTile(xf, yf, "terrain", "3");
            //map.setTile(xf, yf, "A", "yellow", -1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}