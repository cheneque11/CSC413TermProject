package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public class PlayerTank extends Tank {

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameState gameState) {

        if (GameState.up) {

            moveForward();

        }
        if (GameState.down) {

            moveBackward();
        }
        if (GameState.left) {

            turnLeft();
        }
        if (GameState.right) {

            turnRight();
        }

        if (GameState.shoot) {
            if(GameState.coolDown < 0){

                shoot(gameState);
                GameState.coolDown();
            }
        }
        if(GameState.d){
            GameDriver.gameState.reset();
        }
        GameState.playerCoolDown--;
    }


}
