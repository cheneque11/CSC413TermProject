package edu.csc413.tankgame.model;


import edu.csc413.tankgame.GameDriver;
import edu.csc413.tankgame.view.RunGameView;

public class AiTank extends Tank {


    public AiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameState gameState) {

//                super.move(gameState);

        PlayerTank playerTank = (PlayerTank) gameState.getEntity(GameState.PLAYER_TANK_ID);


        if (playerTank != null) {
            if (playerTank.getY() < getY() + 100) {
                moveForward();
                moveForward();
                turnLeft();
                moveBackward();
                moveBackward();
                turnRight();
            }else{
                moveForward();
                turnRight();
                turnLeft();
            }
            double dx = playerTank.getX() - getX();
            double dy = playerTank.getY() - getY();

            double angleToPlayer = Math.atan2(dy, dx);
            double angleDifference = getAngle() - angleToPlayer;

            angleDifference -=
                    Math.floor(angleDifference / Math.toRadians(360.0)
                            + 0.5) * Math.toRadians(360.0);

            if (angleDifference < -Math.toRadians(3.0)) {
                turnRight();
            } else if (angleDifference > Math.toRadians(3.0)) {
                turnLeft();
            }
        }
        if (GameState.coolDown < 0) {
            shoot(gameState);
            GameState.coolDown();

        }
        GameState.coolDown -= 1;
    }

    public void getLife() {

        Lives.setTankId(getId());
        Lives life = new Lives(GameState.RED_ID,
                RunGameView.RED_INITIAL_X, RunGameView.RED_INITIAL_Y,
                0,
                Lives.getTankId()
        );
        GameDriver.gameState.addRedLives(life);

    }

}