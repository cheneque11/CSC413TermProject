package edu.csc413.tankgame.model;


import edu.csc413.tankgame.GameDriver;
import edu.csc413.tankgame.WallImageInfo;

public class Walls extends Entity {


    public Walls(String id, double x, double y, double angle) {
        super(id, x, y, angle);
        id = getUniqueId();
    }

    public static final String WALL_ID_PREFIX = "wall-";
    private static long uniqueId = 0L;

    @Override
    public void move(GameState gameState) {
    }

    public static String getUniqueId() {
        String str = String.valueOf(uniqueId++);
        System.out.println(WALL_ID_PREFIX + str);
        return WALL_ID_PREFIX + uniqueId;
    }

    @Override
    public String toString() {
        String str = String.valueOf(uniqueId++);
        System.out.println(WALL_ID_PREFIX + str);
        return WALL_ID_PREFIX + uniqueId;
    }


    public double getXBound(){

        double x = getX() + 32.00;

        return x;
    }


    public double getYBound(){

        double y = getY() + 32.00;
        return y;
    }

    public static void addWalls(){
        int i = 0;

        System.out.println("walls!!" + WallImageInfo.readWalls().size() +"\n");
        for(WallImageInfo entity: WallImageInfo.readWalls()){
            System.out.println(entity.getX());
            System.out.println(entity.getImageFile());
            Entity wall = new Walls(i +"", entity.getX(), entity.getY(), 0);
            GameDriver.gameState.addEntities(wall);
            GameDriver.runGameView.addDrawableEntity(
                    i+"",
                    entity.getImageFile(),
                    entity.getX(),
                    entity.getY(),
                    0
            );

            i++;
        }
    }
}
