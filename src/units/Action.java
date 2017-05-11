package units;

/**
 * Created by toon on 11/05/17.
 */
public class Action {

    public enum ActionType {
        Move,
        Attack
    }

    private int x;
    private int y;
    private ActionType action;

    public Action(int x, int y, ActionType a) {
        this.x = x;
        this.y = y;
        this.action = a;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ActionType getAction() {
        return action;
    }
}
