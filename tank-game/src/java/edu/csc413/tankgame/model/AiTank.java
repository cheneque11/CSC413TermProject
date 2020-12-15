package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public class AiTank extends Tank {


    public AiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameState gameState) {
            //        super.move(gameState);

//     PlayerTank playerTank = (PlayerTank) gameState.getEntity(GameState.PLAYER_TANK_ID);

            double dx = GameDriver.playerTank.getX() - getX();
            double dy = GameDriver.playerTank.getY() - getY();

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
//        shoot(gameState);

            GameState.checkBulletsBoundries();

        }

}