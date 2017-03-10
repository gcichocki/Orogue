import java.awt.*;

/**
 * Created by FatePc on 3/4/2017.
 */
public class MapMain {

    public static void main(String[] args) {
        Map map = new Map(26, 50);

        map.setTile(20 , 15, "@", Color.blue);
        map.setTile(20 -1, 15, "#", Color.RED);
    }
}
