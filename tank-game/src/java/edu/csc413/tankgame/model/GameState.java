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
    public static final String SECOND_AI_TANK_ID = "second-ai";

    public static boolean up ;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    public static boolean d;
    public static boolean shoot;
    public static boolean escape;
    public static int coolDown = 300;
    public static int playerCoolDown;

    //not proper way to do it we want to got hrough all images not just tanks
    //--------------------------------------
    private final List<Entity> entities = new ArrayList<>();

    public void addEntities(Entity entity) {
        entities.add(entity);
    }

    //    public void addEntity(Entity entity){
//        entities.add(entity);
//    }
    public List<Entity> getEntities() {
        return entities;
    }

    public static List<Entity> shells = new ArrayList<>();
    public  List<Entity> removeShellsList = new ArrayList<>();

    public void addShells(Entity shell) {
        System.out.println("Adding shell: " + shell + "\n");
        shells.add(shell);
    }

    public void addRemoveShellsList(Entity shell) {
        removeShellsList.add(shell);
    }

    public void clearShells(){
        shells.clear();
    }
    public void clearRemovedShells(){
        removeShellsList.clear();
    }
    public List<Entity> getShells() {
        for(Entity e: shells) {
            System.out.println("get shells list:" + e);
        }
        return shells;
    }

    public Entity getEntity(String id) {
        return (Entity) entities.stream()
                .filter(entity -> entity.getId().equals(id));
    }
//-------------------------------------------------


    public static void reset(){


    }

    public static void notUpPressed() {

        up = false;
    }
    public static void setUpPressed(){

        up = true;
    }

    public static void notDownPressed() {
//        System.out.println("getX(): " + getX() + "\n");
        System.out.println("downPressed\n");

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
//        System.out.println("getX(): " + getX() + "\n");
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
    public static void escapePressed() {
        escape = true;
    }
    public static void escapeNotPressed() {
        escape =false;
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


}
