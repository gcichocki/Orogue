package units;

import adapter.Controller;
import map.Map;
import map.Tuple;

import java.util.ArrayList;
import java.util.HashMap;

public class Master {
    public boolean IACanSeeEnemy(int id, Tuple<Integer, Integer> player_pos) {
        // On compare les distances au carré pour économiser l'opération de racine carrée (rayon fov = 8)
        // Si jamais l'ennemi est dans le cercle mais caché par un obstacle le serveur ne renvoie pas de ligne "ennemy"
        // et donc cette fonction n'est pas exécutée, inutile donc de tester les obstacles ici
        //System.out.println("[List_units] " + listUnits.toString());
        //System.out.println("[Player_pos] " + player_pos.toString());
        //System.out.println("[id_unit] " + id);
        //System.out.println(listUnits.get(id));
        return Math.pow((listUnits.get(id).getPosX() - player_pos.x), 2.0) + Math.pow((player_pos.y - listUnits.get(id).getPosY()), 2.0) <= 64.0d;
    }

    public void Communicate(int id_unit) {
        Enemy unit = listUnits.get(id_unit);
        if (unit.getTimelapse() >= 5) {
            for(java.util.Map.Entry<Integer, Enemy> entry : this.getListUnits().entrySet()) {
                Integer other_id = entry.getKey();
                Enemy other_unit = entry.getValue();

                if (this.IACanSeeEnemy(id_unit, new Tuple<>(other_unit.getPosX(), other_unit.getPosY()))) {
                    // si l'IA peut voir l'autre IA
                    if (unit.getTimelapse() > other_unit.getTimelapse()) {
                        // >
                        unit.getMapController().receiveDico(other_unit.getDicoProba());
                    } else if(unit.getTimelapse() < other_unit.getTimelapse()) {
                        // <
                        other_unit.getMapController().receiveDico(unit.getDicoProba());
                    } else {
                        // ==
                        // NE FAIT RIEN
                    }
                }
            }
        }
    }

    public enum MasterState {
        Idle,
        On
    }
    private HashMap<Integer, Enemy> listUnits;

    public HashMap<Integer, Enemy> getListUnits() {
        return listUnits;
    }

    private Map map;
    private MasterState state;
    private HashMap<Integer, ArrayList<Tuple<Integer, Integer>>> tmpNewTilesByUnit;
    private HashMap<Integer, Tuple<Integer, Integer>> posEnemyByUnit;

    public void hide(int x, int y) {
        map.hide(x, y);
    }

    public void addNewTilesByUnit(int id, Tuple<Integer, Integer> newTile) {
        //tmpNewTilesByUnit.put(id, tmpNewTiles);
        tmpNewTilesByUnit.putIfAbsent(id, new ArrayList<>());

        tmpNewTilesByUnit.get(id).add(newTile);
    }

    public void setPosEnemyByUnit(int id, Tuple<Integer,Integer> posEnemy) {
        posEnemyByUnit.put(id, posEnemy);
    }

    public Action playUnit(int id) {
        // show the tiles saw by this unit
        //System.out.println("[List_Case_Updated] " + tmpNewTilesByUnit.get(id).toString());
        // if we didn't saw the enemy
        posEnemyByUnit.putIfAbsent(id, new Tuple<>(-1, -1));
        //System.out.println("[Pos_Enemy_by_unit] " + posEnemyByUnit.get(id).toString());

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

    public int getNbIA() {
        return listUnits.size();
    }

    public void updateMap(int x, int y, String type_unit, String type) {
        map.updateTile(x, y, type_unit, type);
    }

    public void updateMapOccupied(int x, int y, boolean value) {
        map.updateTileOccupied(x, y, value);
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
