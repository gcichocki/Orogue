package utils;

public class Log {

    public enum LOG_LEVEL {
        NONE,
        NORMAL,
        NORMAL_LINE,
        ALL
    }

    private static LOG_LEVEL level;

    public static void setLevel(LOG_LEVEL lvl) {
        level = lvl;
    }

    public static void coordonate(String label, int x, int y) {
        if(level != LOG_LEVEL.NONE) {
            System.out.println("[" + label + "] x=" + x + " y=" + y);
        }
    }

    public static void d(String message) {
        if(level == LOG_LEVEL.ALL) {
            System.out.println("[DEBUG] " + message);
        }
    }

    public static void move(String move) {
        if(level != LOG_LEVEL.NONE) {
            System.out.println("[MOVE] " + move);
        }
    }

    public static void put(String label, String message) {
        if(level != LOG_LEVEL.NONE) {
            System.out.println("[" + label + "] " + message);
        }
    }

    public static void line(String line) {
        if(level == LOG_LEVEL.ALL || level == LOG_LEVEL.NORMAL_LINE) {
            System.out.println("[SERVER_INPUT] " + line);
        }
    }
}
