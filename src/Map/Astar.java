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
        double tmpGscore = 0d;

       ArrayList<Tile> closedSet = new ArrayList<Tile>();
       MinHeapLocator openSet = new MinHeapLocator();

       this.initAstar();
       gscore[origin.getPosX()][origin.getPosY()] = 0d;
       fscore[origin.getPosX()][origin.getPosY()] = heuristic(origin, dest);
       openSet.Insert(this.origin, fscore[origin.getPosX()][origin.getPosY()]);
       Tile current = origin;

       while(!openSet.isEmpty()){
            current = openSet.getMin();

            if(current == dest) {
                return FinalPath();
            }

            openSet.removeMin();
            closedSet.add(current);

            for(Tile t : getNeighbours(current)){
                if(!closedSet.contains(t)){
                    tmpGscore = gscore[current.getPosX()][current.getPosY()] + 1d;
                    if(!openSet.contains(t)){
                        fscore[t.getPosX()][t.getPosY()] = tmpGscore + heuristic(t, dest);
                        openSet.Insert(t, fscore[t.getPosX()][t.getPosY()]);
                    } else if( tmpGscore < gscore[t.getPosX()][t.getPosY()]){
                        cameFrom[t.getPosX()][t.getPosY()] = current;
                        gscore[t.getPosX()][t.getPosY()] = tmpGscore;
                        fscore[t.getPosX()][t.getPosY()] = gscore[t.getPosX()][t.getPosY()] + heuristic(t, dest);
                    }
                }
            }

       }
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
        
        Tile n = map.getTile(t.getPosX()-1, t.getPosY());
        if(t.getPosX() != 0 && !n.isObstacle())
            neighbours.add(n);

        n = map.getTile(t.getPosX(), t.getPosY()-1);
        if(t.getPosY() != 0 && !n.isObstacle())
            neighbours.add(n);

        n = map.getTile(t.getPosX()+1, t.getPosY());
        if(t.getPosX() != map.getRows() && !n.isObstacle())
            neighbours.add(n);

        n = map.getTile(t.getPosX(), t.getPosY()+1);
        if(t.getPosY() != map.getCols() && !n.isObstacle())
            neighbours.add(n);

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