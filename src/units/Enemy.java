package units;

import astar.Path;

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

    public Enemy(int id, int hp, int posX, int posY, char symbole) {
        this.id = id;
        this.hp = hp;
        this.posX = posX;
        this.posY = posY;
        this.state = AgentState.Idle;
        this.symbole = symbole;
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

    public void updatePlan() {

    }

    public int getId() {
        return id;
    }

    public char getSymbole() {
        return symbole;
    }

    public void action() {
        switch(state){
            case Idle: break;
            case Explore: break;
            case Rush: break;
            case Search: break;
        }

    }



}
