package Map;

import javax.swing.*;
import java.awt.*;

/**
 * Created by FatePc on 3/4/2017.
 */
public class Tile {

    // symb ascii
    private String ascii;

    // color
    private Color color;

    // x position on the map
    private int posX;

    // y position on the map
    private int posY;

    /**
     * Constructor of a tile, with color and pos
     * @param ascii
     * @param color
     * @param posX
     * @param posY
     */
    public Tile(String ascii, Color color, int posX, int posY) {
        this.ascii = ascii;
        this.color = color;
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {

        return posX;
    }

    /**
     * Default constructor, make an '.' with black color (unknow terrain)
     * @param posX
     * @param posY
     */
    public Tile(int posX, int posY) {
        this.ascii = ".";
        this.color = Color.GRAY;
        this.posX = posX;
        this.posY = posY;
    }

    public String getAscii() {
        return ascii;
    }

    public Color getColor() {
        return color;
    }

    public void setAscii(String ascii) {
        this.ascii = ascii;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
