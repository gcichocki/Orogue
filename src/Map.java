import java.awt.*;

/**
 * Created by FatePc on 3/4/2017.
 */
public class Map {

    // Map
    private Tile[][] map;

    private int rows;

    private int cols;

    /**
     * Create a map form the size given in parameter, with only unknow cases
     * @param cols
     * @param rows
     */
    public Map(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        map = new Tile[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = new Tile(i, j);
            }
        }

        MapGui mapGui = new MapGui(this);
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
    public void setTile(int posX, int posY, String ascii, Color color) {
        map[posX][posY].setAscii(ascii);
        map[posX][posY].setColor(color);
    }
}
