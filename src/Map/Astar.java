package Map;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.sqrt;

/**
 * Created by toon on 10/03/17.
 */
public class Astar {

    private Tile dest;
    private Tile origin;

    private double[][] fscore;
    private double[][] gscore;
    private Tile[][] cameFrom;

    public Astar(Tile dest, Tile origin, int rows, int cols) {
        this.dest = dest;
        this.origin = origin;
        this.fscore = new double[rows][cols]; // 0 par défaut
        this.gscore = new double[rows][cols];
        this.cameFrom = new Tile[rows][cols]; // null par défaut
    }



    public Path runAstar(){
        return null;
    }


    /**
     * Reconstruct the final path after an a* algorithm
     * @return Path finalPath
     */
    Path FinalPath(){
        Tile aux = dest;
        Path path = new Path();
        while(aux != origin){
            path.add(aux);
            aux = cameFrom[aux.getPosX()][aux.getPosY()];
        }
        path.add(aux);
        return path;
    }


    /**
     * Calculate the distance between two tiles on our map
     * @param origin
     * @param dest
     * @return manhattan distance
     */
    double heuristic(Tile origin, Tile dest){
        return StrictMath.abs(dest.getPosX() - origin.getPosX()) + StrictMath.abs(dest.getPosY() - origin.getPosY());
    }


}