package Map;

import java.awt.*;
import java.util.HashMap;

public class Terrain {

    public static HashMap<Integer, String> terrain_sym;
    static
    {
        terrain_sym = new HashMap<>();
        terrain_sym.put(0, "~");
        terrain_sym.put(1, "♣");
        terrain_sym.put(2, ".");
        terrain_sym.put(3, "^");
        terrain_sym.put(4, "%");
        terrain_sym.put(5, ".");
        terrain_sym.put(6, "#");
        terrain_sym.put(7, "+");
    }

    public static HashMap<Integer, Color> terrain_color;
    static
    {
        terrain_color = new HashMap<>();
        terrain_color.put(0, Color.BLUE);
        terrain_color.put(1, Color.GREEN);
        terrain_color.put(2, Color.GRAY);
        terrain_color.put(3, new Color(211, 84, 0));
        terrain_color.put(4, Color.DARK_GRAY);
        terrain_color.put(5, Color.DARK_GRAY);
        terrain_color.put(6, Color.RED);
        terrain_color.put(7, Color.CYAN);
    }
}
