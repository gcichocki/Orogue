package Map;

import java.awt.*;
import java.util.HashMap;

public class Character {
    public static HashMap<String, String> character_to_sym;
    static
    {
        character_to_sym = new HashMap<>();
        character_to_sym.put("h", "\uD83D\uDE08");
        character_to_sym.put("@", "@");
    }

    public static HashMap<String, Color> character_to_color;
    static
    {
        character_to_color = new HashMap<>();
        character_to_color.put("h", Color.YELLOW);
        character_to_color.put("@", Color.BLUE);
    }
}
