package units;

import adapter.Controller;
import map.Map;

import java.util.HashMap;

public class Master {
    public void hide(int x, int y) {
        map.hide(x, y);
    }

    public enum MasterState {
        Idle,
        On
    }
    private HashMap<Integer, Enemy> listUnits;
    private Map map;
    private MasterState state;

    public Master() {
        this.listUnits = new HashMap<>();
        this.state = MasterState.Idle;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public void notifyUnit() {

    }

    public void updateMap(int x, int y, String type_terrain, int type) {
        map.updateTile(x, y, type_terrain, type);
    }

    public void updateMap(int x, int y, String type_unit, String unit) {
        map.updateTile(x, y, type_unit, unit);
    }

    public void updateEntity(int unitId, int hp, int posX, int posY, char symbole) {
        if(listUnits.containsKey(unitId))
        {
            Enemy unit = listUnits.get(unitId);
            unit.setPos(posX, posY);
            unit.setHp(hp);
            Controller.getInstance().getMapGUI().updateUnit(unit);
        }
        else {
            Enemy unit = new Enemy(unitId, hp, posX, posY, symbole);
            listUnits.put(unitId, unit);
            Controller.getInstance().getMapGUI().addUnit(unit);
        }
        //printListUnit();
    }

    public void printListUnit() {
        System.out.println("Liste des unités : \n -------------- \n\n");

        for(java.util.Map.Entry<Integer, Enemy> entry : listUnits.entrySet()) {
            Enemy e = entry.getValue();

            System.out.println("[" + e.getId() +"] x=" + e.getPosX() + " y=" + e.getPosY() +" hp=" + e.getHp() + " symbole=" + e.getSymbole());
        }

        System.out.println("\nFin liste des unités \n ------------- \n\n");
    }
}
