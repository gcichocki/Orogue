package Map;

import java.util.ArrayList;

/**
 * Created by toon on 10/03/17.
 */
public class Path {

    private ArrayList<Tile> path;

    public Path() {
        this.path = new ArrayList<Tile>();
    }

    public ArrayList<Tile> getPath() {
        return path;
    }

    public void add(Tile t){
        this.path.add(0,t);
    }
}
