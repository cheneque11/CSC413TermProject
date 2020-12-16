package edu.csc413.tankgame.model;

/**
 * Model class representing a shell that has been fired by a tank. A shell has a position and an angle, as well as a
 * speed. Shells by default should be unable to turn and only move forward.
 */
// TODO: Notice that Shell has a lot in common with Tank.
//  For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public class Shell extends Entity{
    public static final String SHELL_ID_PREFIX = "shell-";
    private static final double MOVEMENT_SPEED = 4.0;

    private static long uniqueId = 0L;

    public Shell(String id, double x, double y, double angle) {
        super(id,x, y, angle);
        id = getUniqueId();

    }
    @Override
    public void move(GameState gameState){


        moveForward();
    }

    public double getXBound(){

        double x = getX() + 24.00;

        return x;
    }


    public double getYBound(){

        double y = getY() + 24.00;
        return y;
    }
    public static String getUniqueId() {

        String str = String.valueOf(uniqueId++);
        System.out.println(SHELL_ID_PREFIX + str);
        return SHELL_ID_PREFIX + uniqueId;
    }

    @Override
    public String toString() {
        String str = String.valueOf(uniqueId++);
        System.out.println(SHELL_ID_PREFIX + str);
        return SHELL_ID_PREFIX + uniqueId;
    }


}
