package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public abstract class Entity {

    private final String id;
    private double x;
    private double y;
    private double angle;

    private static final double MOVEMENT_SPEED = 2.0;
    protected static final double TURN_SPEED = Math.toRadians(3.0);

    public Entity(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setXYAngle(double x, double y, double angle){
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    public void setAngle(double angle){

        this.angle = angle;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }



    public double getAngle() {

        if(angle > 6){
            setAngle(0);
        }
        if(angle < 0){
            setAngle(6);
        }
        return angle;
    }


    public abstract void move(GameState gameState);

    protected void moveBackward() {
        if (x > GameState.TANK_X_UPPER_BOUND) {
            this.x = GameState.TANK_X_UPPER_BOUND;
        } else if (x < GameState.TANK_X_LOWER_BOUND) {
            this.x = GameState.TANK_X_LOWER_BOUND;
        }
        if (y > GameState.SHELL_Y_UPPER_BOUND) {
            this.y = GameState.TANK_Y_UPPER_BOUND;
        } else if (y < GameState.TANK_Y_LOWER_BOUND) {
            this.y = GameState.TANK_Y_LOWER_BOUND;
        } else {
            setX(x -= MOVEMENT_SPEED * Math.cos(angle));
            setY(y -= MOVEMENT_SPEED * Math.sin(angle));
        }
    }
    protected void moveForward() {
//        System.out.println("inside move forward\n");
        if (x > GameState.TANK_X_UPPER_BOUND) {
            this.x = GameState.TANK_X_UPPER_BOUND;
            GameDriver.gameState.getShells().clear();
        } else if (x < GameState.TANK_X_LOWER_BOUND) {
            this.x = GameState.TANK_X_LOWER_BOUND;
            GameDriver.gameState.getShells().clear();
        }
        if (y > GameState.SHELL_Y_UPPER_BOUND) {
            this.y = GameState.TANK_Y_UPPER_BOUND;
            GameDriver.gameState.getShells().clear();
        } else if (y < GameState.TANK_Y_LOWER_BOUND) {
            this.y = GameState.TANK_Y_LOWER_BOUND;
            GameDriver.gameState.getShells().clear();
        } else {
            setX(x += MOVEMENT_SPEED * Math.cos(angle));
            setY(y += MOVEMENT_SPEED * Math.sin(angle));
        }
    }



    protected void turnLeft() {
        setAngle(this.angle -= TURN_SPEED);
    }


    protected void turnRight() {
        setAngle(this.angle += TURN_SPEED);
    }
    // The following methods will be useful for determining where a
    // shell should be spawned when it
    // is created by this tank. It needs a slight offset
    // so it appears from the front of the tank,
    // even if the tank is rotated. The shell should
    // have the same angle as the tank.

    protected void setShellX(double x){
        this.x = x;
    }
    protected void setShellY(double y){
        this.y = y;
    }
    protected double getShellX() {

        return getX() + 30.0 * (Math.cos(getAngle()) + 0.5);
    }

    protected double getShellY() {
        return getY() + 30.0 * (Math.sin(getAngle()) + 0.5);
    }


    public abstract double getXBound();

    public abstract double getYBound();


}


