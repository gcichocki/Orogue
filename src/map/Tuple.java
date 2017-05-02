package map;

/**
 * Created by tahel on 02/05/17.
 */
public class Tuple<X, Y> {

    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x=" + x + " y=" + y;
    }
}
