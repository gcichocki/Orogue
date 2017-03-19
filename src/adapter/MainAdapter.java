package adapter;

import Map.*;

import java.awt.*;

/**
 * Created by alex on 10/03/17.
 */
public class MainAdapter {

    public static void main(String[] args) {
        Map map = new Map(250, 180);
        System.out.println("Map génerer ");

        Adapter a = new Adapter(1029);

        /*for(int i =0; i<30; i++){
            for(int j =0; j<30 ; j++){
                map.setTile(i, j, ".", "red", -1);
            }
        }

        //map.setTile(20 , 15, "@", Color.blue, -1);
        map.setTile(0, 15, "#", "red", 6);
        map.setTile(1, 15, "#", "red", 6);
        map.setTile(2, 15, "#", "red", 6);
        map.setTile(3, 15, "#", "red", 6);
        map.setTile(4, 15, "#", "red", 6);
        map.setTile(5, 15, "#", "red", 6);
        map.setTile(6, 15, "#", "red", 6);
        map.setTile(7, 15, "#", "red", 6);
        map.setTile(8, 15, "#", "red", 6);
        map.setTile(9, 15, "#", "red", 6);

        map.setTile(0 , 0, "@", "blue", -1);
        map.setTile(0 , 29, "@", "blue", -1);

        Astar aetoile = new Astar(map, map.getTile(0,0), map.getTile(3,0));

        Path chemin = new Path();
        chemin = aetoile.runAstar();

        if(chemin==null)
            System.out.println("regardons si ça marche : " );

        for(Tile t : chemin.getPath()) {
            map.setTile(t.getPosX() , t.getPosY(), "%", "blue", -1);
        }

        */

    }
}
