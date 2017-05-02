import adapter.Controller;

/**
 * Created by tahel on 02/05/17.
 */
public class MainIA {
    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        controller.setIA(true);
    }
}
