package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public class PlayerTank extends Tank {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private double x;
    private double y;

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);

        this.x = x;
        this.y = y;

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
        setY(getY()-2);
        setAngle(300);
        this.up = up;
        System.out.println("inside boolean setupPressed\n");
    }
    public void setDownPressed(boolean down){
        setY(getY()+2);
        setAngle(190);
        this.down = down;
    }
    public void setLeftPressed(boolean left){
        setX(getX()-2);
        setAngle(135);
        this.left = left;
    }
    public void setRightPressed(boolean right){
        setX(getX()+2);
        setAngle(0);
        this.right = right;
    }

    public void setRightDownPressed(boolean right, boolean down){
        setX(getX()+2);
        setY(getY()+2);

    }

}
