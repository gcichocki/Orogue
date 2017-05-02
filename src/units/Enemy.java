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
        Search
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
            this.state = AgentState.Rush;
        } else {
            this.state = AgentState.Search;
        }
        this.mapController.updateProba();
    }


    public Tuple<Integer, Integer> action(ArrayList<Tuple<Integer, Integer>> list, Tuple<Integer, Integer> playerPosition) {
        updatePlan(list, playerPosition);
        switch(state){
            case Idle:
                break;
            case Explore:
                //discover new map
                break;
            case Rush:
                rush(playerPosition);
                break;
            case Search:
                search(list);
                break;
        }
        System.out.println("path size : " + path.getPath().toString());
        Tile dest = path.pop();
        return new Tuple<>(dest.getPosX(), dest.getPosY());
    }

    public void rush(Tuple<Integer, Integer> playerPosition){
        this.mapController.playerSpotted(playerPosition.x, playerPosition.y);
        Astar aetoile = new Astar(
                this.master.getMap(),
                this.master.getMap().getTile(this.getPosX(), this.getPosY()),
                this.master.getMap().getTile(playerPosition.x, playerPosition.y));
        path = aetoile.runAstar();
        path.pop();
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
    public void search(ArrayList<Tuple<Integer, Integer>> list){
        mapController.updateProbasToZero(list);
        Proba p = this.mapController.pickDirection();
        System.out.println("Proba : " + p.toString());
        Astar aetoile = new Astar(
                this.master.getMap(),
                this.master.getMap().getTile(this.getPosX(), this.getPosY()),
                this.master.getMap().getTile(p.getX(), p.getY()));
        path = aetoile.runAstar();
        path.pop();
    }

}
