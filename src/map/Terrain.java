package map;

import java.util.HashMap;

public class Terrain {

    public static HashMap<String, String> terrain_sym;
    static
    {
        terrain_sym = new HashMap<>();
        terrain_sym.put("0", "~");
        terrain_sym.put("1", "♣");
        terrain_sym.put("2", ".");
        terrain_sym.put("3", "^");
        terrain_sym.put("4", "%");
        terrain_sym.put("5", ".");
        terrain_sym.put("6", "#");
        terrain_sym.put("7", "+");
        terrain_sym.put("h", "\uD83D\uDE08");
        terrain_sym.put("@", "@");
    }

    public static HashMap<String, String> terrain_color;
    static
    {
        terrain_color = new HashMap<>();
        terrain_color.put("0", "blue");
        terrain_color.put("1", "green");
        terrain_color.put("2", "gray");
        terrain_color.put("3", "maroon");
        terrain_color.put("4", "white");
        terrain_color.put("5", "dark_grey");
        terrain_color.put("6", "red");
        terrain_color.put("7", "cyan");
        terrain_color.put("h", "yellow");
        terrain_color.put("@", "blue");
    }

}
