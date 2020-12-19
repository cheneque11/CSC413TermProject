package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

public class PlayerTank extends Tank {
    private static final double MOVEMENT_SPEED = 2.0;

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

        GameState.playerCoolDown--;
    }

    public void getLife() {

        Lives.setTankId(getId());
        Lives life = new Lives(GameState.BLUE_ID,
                RunGameView.BLUE_INITIAL_X, RunGameView.BLUE_INITIAL_Y,
                0,
                Lives.getTankId()
        );
        GameState.addBlueLives(life);
    }
}
