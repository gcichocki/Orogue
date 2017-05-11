package units;

import adapter.Controller;
import map.Map;
import map.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class Master {
    public enum MasterState {
        Idle,
        On
    }
    private HashMap<Integer, Enemy> listUnits;
    private Map map;
    private MasterState state;
    private HashMap<Integer, ArrayList<Tuple<Integer, Integer>>> tmpNewTilesByUnit;
    private HashMap<Integer, Tuple<Integer, Integer>> posEnemyByUnit;

    public void hide(int x, int y) {
        map.hide(x, y);
    }

    public void setNewTilesUnit(int id, ArrayList<Tuple<Integer, Integer>> tmpNewTiles) {
        tmpNewTilesByUnit.put(id, tmpNewTiles);
    }

    public void setPosEnemyByUnit(int id, Tuple<Integer,Integer> posEnemy) {
        posEnemyByUnit.put(id, posEnemy);
    }

    public Action playUnit(int id) {
        // show the tiles saw by this unit
        System.out.println(tmpNewTilesByUnit.get(id).toString());
        System.out.println(posEnemyByUnit.get(id).toString());

        return listUnits.get(id).action(tmpNewTilesByUnit.get(id), posEnemyByUnit.get(id));
    }



    public Master() {
        this.listUnits = new HashMap<>();
        this.state = MasterState.Idle;
        this.tmpNewTilesByUnit = new HashMap<>();
        this.posEnemyByUnit = new HashMap<>();
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public void notifyUnit() {

    }

    public void updateMap(int x, int y, String type_unit, String type) {
        map.updateTile(x, y, type_unit, type);
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
            Enemy unit = new Enemy(unitId, hp, posX, posY, symbole, this);
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
