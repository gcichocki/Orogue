package proba;

import map.Tuple;

import java.util.ArrayList;

/**
 * Created by toon on 21/04/17.
 */
public class MatrixProbaController {

    private MatrixProba matrix;
    private int timelapse = 0;

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
        this.timelapse = 0;
        this.matrix.resetMapProba();
        this.matrix.setProba(x, y, (byte)9);
        this.matrix.smoothMapProba();
        this.matrix.printMatrix();
        this.matrix.printDico();
    }

    public void updateProba(){
        timelapse++;
    }

    /**
     * choose a new direction as a new turn begins
     * @return
     */
    public Proba pickDirection(){
        return this.matrix.pickProba(this.timelapse);
    }

    /**
     * unit reaction upon receiving new data from another unit
     * @param listProba
     */
    //TODO VERIFIER AVEC LE TIMELAPSE CE QU'IL FAUT FAIRE
    public void blendProba(ArrayList<Proba> listProba){
        this.matrix.setListMaxProba(listProba);
        this.matrix.smoothMapProba();
    }

    public void updateProbasToZero(ArrayList<Tuple<Integer, Integer>> listProba){
        this.matrix.resetArrayOfProba(listProba);
    }

}
