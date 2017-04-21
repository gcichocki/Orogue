package proba;

/**
 * Created by toon on 21/04/17.
 */
public class Proba {

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
}
