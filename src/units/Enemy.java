package units;

import astar.Astar;
import astar.Path;
import map.Tile;
import map.Tuple;
import proba.MatrixProbaController;
import proba.Proba;

import java.util.ArrayList;

public class Enemy {
    public enum AgentState {
        Idle,
        Explore,
        Rush,
        Search,
        Attack
    }

    private int id;
    private int hp;
    private int posX;
    private int posY;
    private Path path;
    private AgentState state;
    private char symbole;
    private MatrixProbaController mapController;
    private Master master;

    public Enemy(int id, int hp, int posX, int posY, char symbole, Master master) {
        this.id = id;
        this.hp = hp;
        this.posX = posX;
        this.posY = posY;
        this.state = AgentState.Search;
        this.symbole = symbole;
        this.master = master;
        this.path = new Path();
        this.mapController = new MatrixProbaController(this.master.getMap().getRows(),this.master.getMap().getCols());
    }

    public int getHp() {
        return hp;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public AgentState getState() {
        return state;
    }

    public Path getPath() {
        return path;
    }

    public void setPos(int x, int y) {
        posX = x;
        posY = y;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public int getId() {
        return id;
    }

    public char getSymbole() {
        return symbole;
    }

    public void updatePlan(ArrayList<Tuple<Integer, Integer>> list, Tuple<Integer, Integer> playerPosition) {
        if(playerPosition.x !=-1 && playerPosition.y != -1){
            Tile pos = new Tile(this.posX, this.posY);
            if (pos.isNeighbours(playerPosition)){
                this.state = AgentState.Attack;
            }
            else {
                this.state = AgentState.Rush;
            }
        } else {
            this.state = AgentState.Search;
        }
        this.mapController.updateProba();
    }


    public Action action(ArrayList<Tuple<Integer, Integer>> list, Tuple<Integer, Integer> playerPosition) {
        Action toDo = null;
        updatePlan(list, playerPosition);
        switch(state){
            case Idle:
                break;
            case Attack:
                toDo = attack(playerPosition);
                break;
            case Explore:
                //discover new map
                break;
            case Rush:
                toDo = rush(playerPosition);
                break;
            case Search:
                toDo = search(list);
                break;
        }
        System.out.println("[Path]" + path.getPath().toString());
        return toDo;
    }


    public Action rush(Tuple<Integer, Integer> playerPosition){
        this.mapController.playerSpotted(playerPosition.x, playerPosition.y);
        Astar aetoile = new Astar(
                this.master.getMap(),
                this.master.getMap().getTile(this.getPosX(), this.getPosY()),
                this.master.getMap().getTile(playerPosition.x, playerPosition.y));
        path = aetoile.runAstar();

        Tile dest = path.pop();
        return new Action(dest.getPosX(), dest.getPosY(), Action.ActionType.Move);
    }


    public Action attack(Tuple<Integer, Integer> playerPosition){
        return new Action(playerPosition.x, playerPosition.y, Action.ActionType.Attack);
    }
    /**
     * the unit goal is to discover new map
     */
    public void explore(){
    }


    /**
     * the player is spotted!
     * the unit go in its direction
     */
    public Action search(ArrayList<Tuple<Integer, Integer>> list){
            if(path.isEmpty() || path.getPath().get(0).isObstacle()){
                mapController.updateProbasToZero(list);
                Proba p;
                do{
                    p = this.mapController.pickDirection();
                    System.out.println("[Proba] " + p.toString());
                }while(this.master.getMap().getTile(p.getX(), p.getY()).isObstacle() || (p.getX()==this.getPosX() && p.getY()==this.posY));

                Astar aetoile = new Astar(
                        this.master.getMap(),
                        this.master.getMap().getTile(this.getPosX(), this.getPosY()),
                        this.master.getMap().getTile(p.getX(), p.getY()));
                path = aetoile.runAstar();
            }


            Tile dest = path.pop();
            return new Action(dest.getPosX(), dest.getPosY(), Action.ActionType.Move);
    }


}
