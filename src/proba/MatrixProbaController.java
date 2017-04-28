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

    public void playerSpotted(int x, int y){
        /**
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
    }

    public void updateProba(){
        /**
         * update the map proba of the unit as the new turn beginsssz
         */
    }

    public void blendProba(int x, int y, byte value, int timelapse){
        Proba p = new Proba(x, y, (byte)(value-timelapse));
    }

    public void oldblendProba(ArrayList<Proba> listProba){
        /**
         * Add those probas to the unit mapProba
         * Smooth the result
         */
        this.matrix.setListMaxProba(listProba);
        this.matrix.smoothMapProba();
    }


}
