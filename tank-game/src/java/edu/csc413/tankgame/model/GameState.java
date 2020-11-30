package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.view.StartMenuView;

import java.util.ArrayList;
import java.util.List;

/**
 * GameState represents the state of the game "world." The GameState object tracks all of the moving entities like tanks
 * and shells, and provides the controller of the program (i.e. the GameDriver) access to whatever information it needs
 * to run the game. Essentially, GameState is the "data context" needed for the rest of the program.
 */
public class GameState {
    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width - 100.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height - 120.0;

    public static final double SHELL_X_LOWER_BOUND = -10.0;
    public static final double SHELL_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width;
    public static final double SHELL_Y_LOWER_BOUND = -10.0;
    public static final double SHELL_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height;

    public static final String PLAYER_TANK_ID = "player-tank";
    public static final String AI_TANK_ID = "ai-tank";

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    // TODO: Feel free to add more tank IDs if you want to support multiple AI tanks! Just make sure they're unique.

    // TODO: Implement.
    // There's a lot of information the GameState will
    // need to store to provide contextual information. Add whatever
    // instance variables, constructors, and methods are needed.

    //not proper way to do it we want to got hrough all images not just tanks
    //--------------------------------------
    private final List<Entity> entities = new ArrayList<>();

    public void addEntities(Entity entity){
        entities.add(entity);
    }

    public List<Entity> getEntities(){
        return entities;
    }

    private final List<Tank> tanks = new ArrayList<>();

    public void addTanks(Tank tanks){
        entities.add(tanks);
    }

    public List<Tank> getTanks(){
        return tanks;
    }



    public Entity getEntity(String id){
        return (Entity) entities.stream()
                .filter(entity -> entity.getId().equals(id));
    }
//-------------------------------------------------

    public static double getTankXLowerBound(){
        return TANK_X_LOWER_BOUND;
    }
PlayerTank playerTank;
    public  boolean upPressed(boolean t) {

        playerTank = new PlayerTank(getEntities().get(0).getId(),
                getEntities().get(0).getX(),
                getEntities().get(0).getY(),
                getEntities().get(0).getAngle());


        playerTank.moveForward();
        addEntities(playerTank);
//        getEntities().get(0)
//        System.out.println(getEntities().equals(0));
//        if(!t){
//            System.out.println("inside uppressed\n");
//        }
//        playerTank.moveForward();
//        System.out.println(getEntities().get(0).getId());
//
//        System.out.println("outsideide uppressed\n");
        return up;
    }
    public boolean downPressed(){
        System.out.println("inside down pressed\n");
        return down;

    }

    public boolean leftPressed() {
        System.out.println("inside left pressed\n");
        return left;

    }
    public boolean rightPressed(){
        System.out.println("inside right pressed\n");
        return right;

    }

}
