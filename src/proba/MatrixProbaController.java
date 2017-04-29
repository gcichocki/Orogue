package proba;

import java.util.ArrayList;

/**
 * Created by toon on 21/04/17.
 */
public class MatrixProbaController {

    private MatrixProba matrix;

    public MatrixProbaController(int sizeX, int sizeY) {
        this.matrix = new MatrixProba(sizeX, sizeY);
    }

    /**
     * unit reaction upon spotting the player
     * @param x
     * @param y
     */
    public void playerSpotted(int x, int y){
        /*
         * an unit has spotted the player
         * Reset Map
         * Set 9 on spotted Tile
         * Smoothing
         * Yummy
         */

        this.matrix.resetMapProba();
        this.matrix.setProba(x, y, (byte)9);
        this.matrix.smoothMapProba();
        this.matrix.printMatrix();
        this.matrix.printDico();
    }

    public void updateProba(){
        /*
         * update the map proba of the unit as the new turn begins
         */
    }

    /**
     * choose a new direction as a new turn begins
     * @param value
     * @param timelapse
     * @return
     */
    public Proba pickDirection(byte value, int timelapse){
        return this.matrix.pickProba(value - timelapse);
    }

    /**
     * unit reaction upon receiving new data from another unit
     * @param x
     * @param y
     * @param value
     * @param timelapse
     */
    public void blendProba(int x, int y, byte value, int timelapse){
        Proba p = new Proba(x, y, (byte)(value-timelapse));
    }

    public void oldblendProba(ArrayList<Proba> listProba){
        /*
         * Add those probas to the unit mapProba
         * Smooth the result
         */
        this.matrix.setListMaxProba(listProba);
        this.matrix.smoothMapProba();
    }


}
