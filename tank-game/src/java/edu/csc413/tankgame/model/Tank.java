package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

/**
 * Model class representing a tank in the game. A tank has a position and an angle. It has a movement speed and a turn
 * speed, both represented below as constants.
 */
// TODO: Notice that Tank has a lot in common with Shell. For full credit,
//  you will need to find a way to share code
// between the two classes so that the logic for e.g.
// moveForward, etc. are not duplicated.
public abstract class Tank extends Entity {

    private static final double MOVEMENT_SPEED = 2.0;
    protected static final double TURN_SPEED = Math.toRadians(3.0);

    public Tank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }
    // TODO: The methods below are provided so you don't have to do the
    //  math for movement. However, note that they are


    // protected. You should not be calling these methods directly
// from outside the Tank class hierarchy. Instead,
    // consider how to design your Tank class(es) so that a Tank can represent both a player-controlled tank and an AI
    // controlled tank.

    public void shoot(GameState gameState) {
        Shell shell = new Shell(Shell.getUniqueId(),
                getShellX(), getShellY(),
                getAngle());
        GameDriver.gameState.addShells(shell);
        shell.moveForward();

    }

    public double getXBound(){

        double x = getX() + 55.00;

        return x;
    }


    public double getYBound(){

        double y = getY() + 24.00;
        return y;
    }



}
