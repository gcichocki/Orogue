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

    public Master() {
        this.listUnits = new ArrayList<>();
        this.state = MasterState.Idle;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void addUnit(Enemy e) {
        listUnits.add(e);
    }

    public void notifyUnit() {

    }

    public void updateMap(int x, int y, String character, String color, int type) {
        Map.setTile(x, y, character, color, type);
    }
}
