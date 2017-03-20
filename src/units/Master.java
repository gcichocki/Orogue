package units;

import Map.Map;

import java.util.ArrayList;

public class Master {
    public enum MasterState {
        Idle,
        On
    }
    private ArrayList<Enemy> listUnits;
    private Map map;
    private MasterState state;

    public Master(Map map) {
        this.map = map;
        this.listUnits = new ArrayList<>();
        this.state = MasterState.Idle;
    }

    public void addUnit(Enemy e) {
        listUnits.add(e);
    }

    public void notifyUnit() {

    }

    public updateMap() {

    }
}
