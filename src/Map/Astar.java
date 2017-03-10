package Map;

import java.util.ArrayList;

import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.sqrt;

/**
 * Created by toon on 10/03/17.
 */
public class Astar {

    private Map map;
    private Tile dest;
    private Tile origin;


    private double[][] fscore;
    private double[][] gscore;
    private Tile[][] cameFrom;

    public Astar(Map map, Tile dest, Tile origin) {
        this.map = map;
        this.dest = dest;
        this.origin = origin;
        this.fscore = new double[map.getRows()][map.getCols()]; // 0 par défaut
        this.gscore = new double[map.getRows()][map.getCols()];
        this.cameFrom = new Tile[map.getRows()][map.getCols()]; // null par défaut
    }



    public Path runAstar(){
        return null;
    }


    private void initAstar(){
        for(int i = 0; i < this.map.getRows(); i++){
            for(int j = 0; j < this.map.getCols();j++){
                this.fscore[i][j] = Double.MAX_VALUE;
                this.gscore[i][j] = Double.MAX_VALUE;
            }
        }
    }

    /**
     * Return the list of neighbours of a tile
     * @param t a tile
     * @return the list of neighbours
     */
    private ArrayList<Tile> getNeighbours(Tile t){
        ArrayList<Tile> neighbours = new ArrayList<Tile>();

        if(t.getPosX() != 0)
            neighbours.add(map.getTile(t.getPosX()-1, t.getPosY()));
        if(t.getPosY() != 0)
            neighbours.add(map.getTile(t.getPosX(), t.getPosY()-1));
        if(t.getPosX() != map.getRows() )
            neighbours.add(map.getTile(t.getPosX()+1, t.getPosY()));
        if(t.getPosY() != map.getCols())
            neighbours.add(map.getTile(t.getPosX(), t.getPosY()+1));

        return neighbours;
    }

    /**
     * Reconstruct the final path after an a* algorithm
     * @return Path finalPath
     */
    private Path FinalPath(){
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
    private double heuristic(Tile origin, Tile dest){
        return StrictMath.abs(dest.getPosX() - origin.getPosX()) + StrictMath.abs(dest.getPosY() - origin.getPosY());
    }


}