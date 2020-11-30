package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;
import edu.csc413.tankgame.GameKeyListener;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.view.StartMenuView;

public class PlayerTank extends Tank {
    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }


//    public void move(GameState gameState){
//        super.move(gameState);
//
//        Entity playerTank = gameState.getEntity(GameState.PLAYER_TANK_ID);
//
//        double dx = playerTank.getX() - getX();
//        double dy = playerTank.getY() - getY();
//        double angleToPlayer = Math.atan2(dy, dx);
//        double angleDifference = getAngle() - angleToPlayer;
//        angleDifference -= Math.floor(
//                angleDifference/Math.toRadians(360.0)
//                        + 0.5 * Math.toRadians(360.0));
//        if(angleDifference < (-TURN_SPEED)){
//            turnRight();
//        }else if(angleDifference > TURN_SPEED){
//            turnLeft();
//        }
//
//        double distance = Math.sqrt(dx * dx + dy * dy);
//        if(distance > 400.0){
//            moveForward();
//        }else if(distance < 200.0){
//            moveBackward();
//        }

    @Override
    public void move(GameState gameState){
        //if a specific key is pressed do specific action(player tank)
//        super.move(gameState);


//        if(gameState.upPressed(true)){
//            System.out.println("inside plyer tank move\n");
//            moveForward();
//        }else if (gameState.upPressed(false)){
//            System.out.println("inside plyer tank move\n");
//            moveForward();
//        }
//        if(gameState.downPressed()){
//            moveBackward();
//        }
//        if(gameState.leftPressed()){
//            turnLeft();
//        }
//        if(gameState.rightPressed()){
//            turnRight();
//        }

    }

//    GameDriver gameDriver;
//    @Override
//    protected void moveForward() {
//
//        gameDriver.playerTank.moveForward();
//
//    }
//
//    @Override
//    protected void moveBackward() {
//
//    }
//
//    @Override
//    protected void turnLeft() {
//
//    }
//
//    @Override
//    protected void turnRight() {
//
//    }
}
