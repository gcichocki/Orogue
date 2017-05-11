package map;

import gui.MapGui;

import java.util.ArrayList;

/**
 * Created by FatePc on 3/4/ 17.
 */
public class Map {

    // map.map
    private static Tile[][] map;

    private int width;

    private int height;

    private ArrayList<MapGui> listener;

    public void addListerner(MapGui mapGUI) {
        this.listener.add(mapGUI);
    }

    /**
     * Create a map form the size given in parameter, with only unknow cases
     * @param width
     * @param height
     */
    public Map(int width, int height) {

        this.width = width;
        this.height = height;
        this.listener =  new ArrayList<>();

        map = new Tile[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = new Tile(i, j);
            }
        }
    }


    public int getRows() {
        return height;
    }

    public int getCols() {
        return width;
    }

    /**
     * Return the tile at the given coordonate
     * @param posX
     * @param posY
     * @return the tile at the X, Y pos
     */
    public Tile getTile(int posX, int posY) {
        return map[posX][posY];
    }


    public void updateTile(int x, int y, String type_terrain, String type) {


        String color = Terrain.terrain_color.get(type);
        String symb = Terrain.terrain_sym.get(type);

        if (type_terrain.equals("terrain")) {
            //System.out.println("terrain update : " + Integer.parseInt(type));
            map[x][y].setValue(Integer.parseInt(type));
        } else {
            map[x][y].setValue(-1);
        }

        map[x][y].setColor(color);
        map[x][y].setAscii(symb);
        map[x][y].setHide(false);

        // we notify the map to be updated
        for (MapGui mapGUI: listener
                ) {
            mapGUI.notify(map[x][y]);
        }
    }

    public void hide(int x, int y) {
        map[x][y].setHide(true);
        // we notify the map to be updated
        for (MapGui mapGUI: listener
                ) {
            mapGUI.notify(map[x][y]);
        }
    }
}
