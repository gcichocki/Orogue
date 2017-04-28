package map;

import java.util.ArrayList;

/**
 * Created by FatePc on 3/4/ 17.
 */
public class Map {

    // map.map
    private static Tile[][] map;

    private int rows;

    private int cols;

    private ArrayList<MapGui> listener;

    public void addListerner(MapGui mapGUI) {
        this.listener.add(mapGUI);
    }

    /**
     * Create a map form the size given in parameter, with only unknow cases
     * @param cols
     * @param rows
     */
    public Map(int cols, int rows) {

        this.rows = rows;
        this.cols = cols;
        this.listener =  new ArrayList<>();

        map = new Tile[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = new Tile(i, j);
            }
        }
    }


    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
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

    public void updateTile(int x, int y, String type_unit, String unit) {

        String symb = Character.character_to_sym.get(unit);
        String color = Character.character_to_color.get(unit);

        map[x][y].setValue(-1);
        map[x][y].setColor(color);
        map[x][y].setAscii(symb);

        // we notify the map to be updated
        for (MapGui mapGUI: listener
                ) {
            mapGUI.notify(map[x][y]);
        }
    }

    public void updateTile(int x, int y, String type_terrain, int type) {


        String color = Terrain.terrain_color.get(type);
        String symb = Terrain.terrain_sym.get(type);

        map[x][y].setValue(type);
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
