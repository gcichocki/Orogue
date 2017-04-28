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


    }

    public void updateProba(){
        /**
         * update the map proba of the unit as the new turn beginsssz
         */
    }


    public void blendProba(ArrayList<Proba> listProba){
        /**
         * Add those probas to the unit mapProba
         * Smooth the result
         */
        this.matrix.setListMaxProba(listProba);
        this.matrix.smoothMapProba();
    }


}
