package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public class PlayerTank extends Tank {

    public PlayerTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    static int coolDown = 200;
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
            if(coolDown < 0) {
                shoot(gameState);
                coolDown();
            }
        }
        if(GameState.d){
            GameDriver.gameState.reset();
        }

        coolDown--;
        coolDown();
//        System.out.println("cooldown: " + coolDown + "\n");
//        GameState.checkBulletsBoundries();
//        System.out.println("GameState.shells.size()" + GameState.shells.size());
//        System.out.println("\nGameDriver.gameState.shells.size()" + GameDriver.gameState.shells.size());
    }

    public void coolDown() {


        if(coolDown < 0) {
            coolDown = 200;

            GameDriver.gameState.clearShells();
        }
//        System.out.println("-------------coolDown: " + coolDown + "\n");
    }


}
