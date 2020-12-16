package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public class SecondAi extends Tank {
    private static final double MOVEMENT_SPEED = 2.0;

    public SecondAi(String id, double x, double y, double angle) {
        super(id, x, y, angle);

    }

    @Override
    public void move(GameState gameState) {

//        super.move(gameState);

//     PlayerTank playerTank = (PlayerTank) gameState.getEntity(GameState.PLAYER_TANK_ID);
// To figure out what angle the AI tank needs to face, we'll use the change
// in the x and y axes between the AI and player tanks.
            double dx = GameDriver.playerTank.getX() - getX();
            double dy = GameDriver.playerTank.getY() - getY();
// atan2 applies arctangent to the ratio of the two provided values.
            double angleToPlayer = Math.atan2(dy, dx);
            double angleDifference = getAngle() - angleToPlayer;
// We want to keep the angle difference between -180 degrees and 180
//        degrees
// for the next step. This ensures that anything outside of that range
// is adjusted by 360 degrees at a time until it is, so that the angle is
// still equivalent.
            angleDifference -=
                    Math.floor(angleDifference / Math.toRadians(360.0)
                            + 0.5) * Math.toRadians(360.0);
            // The angle difference being positive or negative determines if we turn
// left or right. However, we don’t want the Tank to be constantly bouncing
// back and forth around 0 degrees, alternating between left and right
// turns, so we build in a small margin of error.
            if (angleDifference < -Math.toRadians(3.0)) {
                turnRight();
            } else if (angleDifference > Math.toRadians(3.0)) {
                turnLeft();
            }


            if (GameState.coolDown < 0) {
                shoot(gameState);
                GameState.coolDown();

            }
            GameState.coolDown -= 1;
        }
    }