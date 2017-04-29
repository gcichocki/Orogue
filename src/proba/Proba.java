package proba;

/**
 * Created by toon on 21/04/17.
 */
public class Proba implements Comparable<Proba> {

    private int x;
    private int y;
    private byte value;

    public Proba(int x, int y, byte value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    /**
     * redefining of this.compareTo(o) for Proba
     * (useful for comparisons in max hep)
     * @param o
     * @return
     */
    @Override
    public int compareTo(Proba o) { return  this.value - o.getValue(); }

    /**
     * my try at redefining this.equals(o) for Proba
     * (useful for contains method in dicoProba)
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj){
        Proba o = (Proba) obj;
        if (this.x == o.getX() && this.y == o.getY() && this.value == o.getValue()){
            return true;
        }
        return false;
    }

    public String toString(){
        return "(x=" + this.x + ", y=" + this.y + ", value=" + this.value + ")";
    }
}
