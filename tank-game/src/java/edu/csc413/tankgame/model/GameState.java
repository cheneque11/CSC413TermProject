package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;
import edu.csc413.tankgame.view.RunGameView;

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
    public static final String SECOND_AI_TANK_ID = "second-ai-tank";
    public static final String BLUE_ID = "blue";
    public static final String RED_ID = "red";
    public static final String GREEN_ID = "green";
    ;
    public static final String POWER_ID = "power";
    public static final String YOUWIN_ID = "youwin";
    public static final String YOULOSE_ID = "youlose";
    public static final String SHELL_EXPLOSION = "shell-expolision";


    // TODO: Feel free to add more tank IDs if you want to support multiple AI tanks! Just make sure they're unique.

    // TODO: Implement.
    // There's a lot of information the GameState will
    // need to store to provide contextual information. Add whatever
    // instance variables, constructors, and methods are needed.
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean d;
    public static boolean shoot;
    public static int coolDown = 300;
    public static int playerCoolDown;

    //not proper way to do it we want to got hrough all images not just tanks
    //--------------------------------------
    private final List<Entity> entities = new ArrayList<>();

    public void addEntities(Entity entity) {
        entities.add(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public static List<Entity> shells = new ArrayList<>();
    public List<Entity> removeShellsList = new ArrayList<>();
    public List<Entity> redLives = new ArrayList<>();
    public static List<Entity> blueLives = new ArrayList<>();
    public List<Entity> greenLives = new ArrayList<>();

    public void addShells(Entity shell) {
//        System.out.println("Adding shell: " + shell + "\n");
        shells.add(shell);
    }

    public void addRemoveShellsList(Entity shell) {
        removeShellsList.add(shell);
    }

    public void clearShells() {
        shells.clear();
    }

    public List<Entity> getShells() {
        for (Entity e : shells) {
//            System.out.println("get shells list:" + e);
        }
        return shells;
    }

    public List<Entity> getRedLives() {
        return redLives;
    }

    public void addRedLives(Entity live) {
        redLives.add(live);
    }

    public List<Entity> getGreenLives() {
        return greenLives;
    }

    public void addGreenLives(Entity live) {
        greenLives.add(live);
    }

    public List<Entity> getBlueLives() {
        return blueLives;
    }

    public static void addBlueLives(Entity live) {
        blueLives.add(live);
    }


    public Entity getEntity(String id) {
//        return (Entity) entities.stream()
//                .filter(entity -> entity.getId().equals(id));
////        return entities.
        Entity en = null;
        for (Entity e : getEntities()) {
            if (e.getId() == id) {
                en = e;
            }
        }
        return en;
    }

//-------------------------------------------------

    public static void notUpPressed() {

        up = false;
    }
    public static void setUpPressed(){

        up = true;
    }

    public static void notDownPressed() {
        down = false;
    }
    public static void setDownPressed() {
//        System.out.println("getX(): " + getX() + "\n");
        System.out.println("downPressed\n");

        down = true;
    }

    public void notDPressed() {
//        System.out.println("getX(): " + getX() + "\n");
        System.out.println("DPressed\n");

        d = false;
    }
    public void setDPressed() {
//       System.out.println("getX(): " + getX() + "\n");
        System.out.println("DNotPressed\n");

        d = true;
    }

    public static void isShootPressed(){
        System.out.println("shooting\n");
        GameDriver.playerTank.shoot(GameDriver.gameState);
        shoot = true;

    }
    public static void notShootPressed(){
        System.out.println("not shooting\n");
//        GameDriver.playerTank.shoot(GameDriver.gameState);
        shoot = false;

    }
    public static void notLeftPressed() {
//        System.out.println("getX(): " + getX() + "\n");
        System.out.println("leftnotPressed\n");

        left = false;
    }
    public static void setLeftPressed() {
//        System.out.println("getX(): " +getX() + "\n");
        System.out.println("LeftPressed\n");

        left = true;
    }
    public static void notRighttPressed() {
//        System.out.println("getX(): " + getX() + "\n");
        System.out.println("leftnotPressed\n");

        right = false;
    }
    public static void setRightPressed() {
//        System.out.println("getX(): " +getX() + "\n");
        System.out.println("LeftPressed\n");

        right = true;
    }
    public static void coolDown() {

        if(coolDown <= 1) {
            coolDown = 300;
            playerCoolDown = 300;
        }
    }
    public void removeEntity(Entity e) {
        entities.remove(e);

    }

    public void clearReadLives() {
        redLives.clear();
    }
    public void clearBLueLives() {
        blueLives.clear();
    }
    public void clearGreenLives() {
        greenLives.clear();
    }

    public void clearEverything(){
        clearReadLives();
        clearBLueLives();
        clearShells();
        clearGreenLives();
        getEntities().clear();
    }

}