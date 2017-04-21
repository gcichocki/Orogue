import Map.Map;
import adapter.Adapter;
import units.Master;

public class Main {
    public static void main(String[] args) {
        Map map = new Map(250, 180);
        Master master = new Master(map);
        Adapter adapter = new Adapter(1029, master);
    }
}
