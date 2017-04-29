package adapter;

import gui.MapGui;
import units.Master;

/**
 * Created by tahel on 21/04/17.
 */
public class Controller {

    private Master master;

    private Adapter adapter;

    private MapGui mapGui;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MapGui getMapGUI() {
        return mapGui;
    }

    public enum Status {
        ACTION,
        WAIT
    }

    private Status status;

    private static Controller INSTANCE = new Controller();

    public static Controller getInstance() {
        return INSTANCE;
    }

    private Controller() {
        master = new Master();
        adapter = new Adapter(1029, master);
        status = Status.WAIT;
    }


    public Adapter getAdapter() {
        return adapter;
    }

    public void initGUI(int x, int y) {
        // we create the GUI
        this.mapGui = new MapGui(x, y);
        // we add the GUI as an listener of the map
        master.getMap().addListerner(this.mapGui);
    }
}
