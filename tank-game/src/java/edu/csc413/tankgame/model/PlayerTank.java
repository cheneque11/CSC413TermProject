package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public class PlayerTank extends Tank {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private double x;
    private double y;
    private static double angle;

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);

        this.x = x;
        this.y = y;
        this.angle = angle;

//        this.moveForward();// = KeyEvent.VK_UP;

    }
    @Override
    public void move(GameState gameState){


//        if(up == true){
//            System.out.println("inside move playertANK\n");
//            moveForward();
//        }

    }

    public void moveUp() {

        GameDriver.playerTank.moveForward();
        this.up = true;
    }
    public  void moveDown() {
        this.down = true;
    }
    public void moveLeft() {
        this.left = true;

    }
    public void moveRight() {

        this.right = false;
    }
    public  void releaseUp() {
        this.up = false;
    }

    public void releaseDown() {
        this.down = false;
    }
    public void releaseLeft() {
        this.left = false;

    }
    public  void releaseRight() {

        this.right = false;
    }

    public void setUpPressed(boolean up){
        setX(getX()-4 * Math.cos(getAngle()));
        setY(getY()-4 * Math.sin(getAngle()));
        this.up = up;

    }
    public void setDownPressed(boolean down){
        setX(getX()+4 * Math.cos(angle));
        setY(getY()+4 * Math.sin(angle));
        this.down = down;
    }
    public void setLeftPressed(boolean left){

        setAngle(angle -= TURN_SPEED);
        this.left = left;
    }
    public void setRightPressed(boolean right){

        setAngle(angle += TURN_SPEED);
        this.right = right;
    }


}
