import adapter.Controller;
import utils.Log;

public class Main {
    public static void main(String[] args) {
        //Master master = new Master();
        //Adapter adapter = new Adapter(1029, master);
        Log.setLevel(Log.LOG_LEVEL.ALL);
        Controller controller = Controller.getInstance();
    }
}
