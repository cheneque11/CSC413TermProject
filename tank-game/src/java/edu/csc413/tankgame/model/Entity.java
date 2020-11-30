package edu.csc413.tankgame.model;

public abstract class Entity{

    private final String id;
    private double x;
    private double y;
    private double angle;


    public Entity(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }


    public abstract void move(GameState gameState);

    // TODO: The methods below are provided so you
    //  don't have to do the
    //  math for movement. However,
    //  note that they are
    // protected. You should not be calling these methods
    // directly from outside the Tank class hierarchy. Instead,
    // consider how to design your Tank class(es) so that a
    // Tank can represent both a player-controlled
    // tank and an AI
    // controlled tank.

    protected abstract void moveForward();

    protected abstract void moveBackward();

    protected abstract void turnLeft();

    protected abstract void turnRight();

    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    private double getShellX() {
        return getX() + 30.0 * (Math.cos(getAngle()) + 0.5);
    }

    private double getShellY() {
        return getY() + 30.0 * (Math.sin(getAngle()) + 0.5);
    }
}
