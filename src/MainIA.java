import adapter.Controller;
import utils.Log;

/**
 * Created by tahel on 02/05/17.
 */
public class MainIA {
    public static void main(String[] args) {
        Log.setLevel(Log.LOG_LEVEL.NONE);
        Controller controller = Controller.getInstance();
        controller.setIA(true);
    }
}
