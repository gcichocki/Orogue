package units;

import Map.Map;

import java.util.HashMap;

public class Master {
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

    /*public void addUnit(Enemy e) {
    }*/

    public void notifyUnit() {

    }

    public void updateMap(int x, int y, String character, String color, int type) {
        Map.setTile(x, y, character, color, type);
    }

    public void updateEntity(int unitId, int hp, int posX, int posY, char symbole) {
        if(listUnits.containsKey(unitId))
        {
            Enemy unit = listUnits.get(unitId);
            unit.setPos(posX, posY);
            unit.setHp(hp);
        }
        else {
            Enemy unit = new Enemy(unitId, hp, posX, posY, symbole);
            listUnits.put(unitId, unit);
        }
        printListUnit();
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
