package Map;

import java.awt.*;

/**
 * Created by FatePc on 3/4/ 17.
 */
public class Map {

    // Map.Map
    private static Tile[][] map;

    private int rows;

    private int cols;

    static MapGui mapGui;

    /**
     * Create a map form the size given in parameter, with only unknow cases
     * @param cols
     * @param rows
     */
    public Map(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        map = new Tile[rows][cols];

        mapGui = new MapGui(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = new Tile(i, j);
                //mapGui.addTile(map[i][j]);
                mapGui.addTile(".", i, j);
            }
        }

        mapGui.initComponents();
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

    /**
     * Change the tile at the given coordonate
     * @param posX
     * @param posY
     */
    public static void setTile(int posX, int posY, String ascii, String color, int value) {
        map[posX][posY].setAscii(ascii);
        map[posX][posY].setValue(value);
        map[posX][posY].setColor(color);
        mapGui.setTile(ascii, color, posX, posY);
    }
}
