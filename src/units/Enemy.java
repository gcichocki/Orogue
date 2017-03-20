package units;

import astar.Path;

public class Enemy {
    public enum AgentState {
        Idle,
        Explore,
        Rush,
        Search
    }

    private int hp;
    private int posX;
    private int posY;
    private Path path;
    private AgentState state;

    public Enemy(int hp, int posX, int posY, Path path) {
        this.hp = hp;
        this.posX = posX;
        this.posY = posY;
        this.path = path;
        this.state = AgentState.Idle;
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

    public void updatePlan() {

    }

    public void action() {

    }
}
