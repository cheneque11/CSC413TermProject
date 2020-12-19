package edu.csc413.tankgame.model;

public class Lives extends Entity{

    private static String tankId;
    public  Lives(String id, double x, double y, double angle, String tankId){
        super(id, x, y, angle);

    }

    public static void  setTankId(String tankId){
        Lives.tankId = tankId;
    }

    public static String getTankId() {
        return tankId;
    }

    @Override
    public void move(GameState gameState) {

    }
    @Override
    public double getXBound() {
        return 0;
    }

    @Override
    public double getYBound() {
        return 0;
    }
}

