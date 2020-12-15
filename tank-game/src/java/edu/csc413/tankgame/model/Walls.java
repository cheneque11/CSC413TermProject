package edu.csc413.tankgame.model;


public class Walls extends Entity {


    public Walls(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        id = getUniqueId();
    }

    public static final String WALL_ID_PREFIX = "wall-";
    private static long uniqueId = 0L;

    @Override
    public void move(GameState gameState) {
    }

    public static String getUniqueId() {
        String str = String.valueOf(uniqueId++);
        System.out.println(WALL_ID_PREFIX + str);
        return WALL_ID_PREFIX + uniqueId;
    }

    @Override
    public String toString() {
        String str = String.valueOf(uniqueId++);
        System.out.println(WALL_ID_PREFIX + str);
        return WALL_ID_PREFIX + uniqueId;
    }
}
