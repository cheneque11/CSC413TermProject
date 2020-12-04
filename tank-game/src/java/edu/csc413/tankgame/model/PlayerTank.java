package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;
import edu.csc413.tankgame.view.RunGameView;

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

    static int id = 0;
    @Override
    public void shoot(GameState gameState) {

        GameDriver.runGameView.addDrawableEntity(
                GameDriver.shell.getUniqueId(),
                RunGameView.SHELL_IMAGE_FILE,
                GameDriver.playerTank.getShellX(),
                GameDriver.playerTank.getShellY(),
                GameDriver.playerTank.getAngle()
        );

        char un[] =GameDriver.shell.getUniqueId().toCharArray();


        for(int i = 0; i < GameDriver.shell.getUniqueId().length(); i++){

            if(GameDriver.shell.getId().length() == 6){
                id = un[i];
                System.out.println("num: " + id +"\n");
            }

            if(GameDriver.shell.getId().length() == 7){
                id = un[i];
                System.out.println("num: " + id +"\n");
            }
            if(GameDriver.shell.getId().length() == 8){
                id = un[i];
                System.out.println("num: " + id +"\n");
            }
        }


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

    public void setUpPressed(boolean up) {
        System.out.println("getX(): " + getX() + "\n");
        System.out.println("upPressed\n");
        if ((int) getX() < 4) {
            setX(4);
        }
        if ((int) getX() > 996) {
            setX(995);
        }
        if ((int) getY() > 696) {
            setY(694);
        }
        if ((int) getY() < 4) {
            setY(4);
        }
        if (getX() > 0 && getX() != 0 && getY() > 0 && getY() != 0) {
            setX(getX() - 4 * Math.cos(getAngle()));
            setY(getY() - 4 * Math.sin(getAngle()));
            this.up = up;
        }
    }
    public void setDownPressed(boolean down){
        System.out.println("getY(): " +getY() + "\n");
        System.out.println("DownPressed\n");
        if((int) getX() < 4){
            setX(4);
        }
        if((int) getX() > 996){
            setX(995);
        }
        if((int) getY() > 696){
            setY(695);
        }
        if((int) getY() < 4 ){
            setY(4);
        }
        if(getX() > 0 && getX() != 0 && getY() > 0 && getY() != 0) {
            setX(getX() + 4 * Math.cos(angle));
            setY(getY() + 4 * Math.sin(angle));
            this.down = down;
        }
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
