package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;

public class AiTank extends Tank {


    public AiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameState gameState) {
        //(ai tank) use gamestate logic, get location, face player
        super.move(gameState);

//                getX();
//        turnRight();
//        Entity playerTank = gameState.getEntity(
//                GameState.PLAYER_TANK_ID);
    }

}