package edu.csc413.tankgame;

import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;

//import edu.csc413.tankgame.view.PrintListener;

/**
 * GameDriver is the primary controller class for the tank game. The game is launched from GameDriver.main, and
 * GameDriver is responsible for running the game loop while coordinating the views and the data models.
 */
public class GameDriver {
    public static MainView mainView;
    public static Shell shell;
    public static RunGameView runGameView;
    public static GameState gameState;
    // TODO: Implement.
    // Add the instance variables, constructors,
    // and other methods needed for this class. GameDriver is the centerpiece
    // for the tank game, and should store and manage the
    // other components (i.e. the views and the models). It also is
    // responsible for running the game loop.

    public static boolean walls;

    static String shId;


    public GameDriver() {

        mainView = new MainView();
        runGameView = mainView.getRunGameView();
        gameState = new GameState();
    }

    public void start() {


        // TODO: Implement.
        // This should set the MainView's screen to
        // the start menu screen.

        mainView.setScreen(MainView.Screen.START_MENU_SCREEN);
//
        runGame();
    }

    static int shellCount = 0;

    public static PlayerTank playerTank;
    public static AiTank ai_tank;
    public static SecondAi secondAi;
    public void runGame() {

        playerTank = new PlayerTank(
                GameState.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_INITIAL_X,
                RunGameView.PLAYER_TANK_INITIAL_Y,
                RunGameView.PLAYER_TANK_INITIAL_ANGLE
        );

        ai_tank = new AiTank(
                GameState.AI_TANK_ID,
                RunGameView.AI_TANK_INITIAL_X,
                RunGameView.AI_TANK_INITIAL_Y,
                RunGameView.AI_TANK_INITIAL_ANGLE
        );

        int i = 0;

        System.out.println("walls!!" + WallImageInfo.readWalls().size() +"\n");
        for(WallImageInfo entity: WallImageInfo.readWalls()){
            System.out.println(entity.getX());
            System.out.println(entity.getImageFile());
            Entity wall = new Walls(i +"", entity.getX(), entity.getY(), 0);
            gameState.addEntities(wall);
            runGameView.addDrawableEntity(
                    i+"",
                    entity.getImageFile(),
                    entity.getX(),
                    entity.getY(),
                    0
            );

            i++;
        }

        secondAi = new SecondAi(
                GameState.SECOND_AI_TANK_ID,
                RunGameView.AI_TANK_INITIAL_X +100,
                RunGameView.AI_TANK_INITIAL_Y,
                RunGameView.PLAYER_TANK_INITIAL_ANGLE
        );

        gameState.addEntities(ai_tank);
        gameState.addEntities(playerTank);
        gameState.addEntities(secondAi);


        /*Draw images on screen*/

        runGameView.addDrawableEntity(GameState.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_IMAGE_FILE,
                playerTank.getX(), playerTank.getY(), playerTank.getAngle()
        );

        runGameView.addDrawableEntity(GameState.AI_TANK_ID,
                RunGameView.AI_TANK_IMAGE_FILE,
                ai_tank.getX(), ai_tank.getY(), ai_tank.getAngle()
        );
        runGameView.addDrawableEntity(GameState.SECOND_AI_TANK_ID,
                RunGameView.AI_TANK_IMAGE_FILE,
                ai_tank.getX()+100, ai_tank.getY()-100, ai_tank.getAngle()
        );

        Runnable gameRunner = () -> {
            while (update()) {
                runGameView.repaint();
                try {
                    Thread.sleep(8L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        };
        new Thread(gameRunner).start();    //create new thread on gameRunner
    }

    // TODO: Implement.
    // update should handle one frame of gameplay. All tanks
    // and shells move one step, and all drawn entities
    // should be updated accordingly. It should return true as
    // long as the game continues.
    private boolean update() {
        //every 8ms ask tanks, shells to move


        for(Entity entity: gameState.getEntities()){
            entity.move(gameState);
        }

        for(Entity entity: gameState.getShells()){
            gameState.addEntities(entity);
            runGameView.addDrawableEntity(
                    entity.getId(),
                    RunGameView.SHELL_IMAGE_FILE,
                    entity.getX(),
                    entity.getY(),
                    entity.getAngle()
            );
//            shId = entity.getId();
//            System.out.println("getId: " + entity.getId());

        }

//        gameState.clearShells();

//

//        runGameView.removeDrawableEntity(gameState.shells.toString());
//        runGameView.removeDrawableEntity(shId);


        //player on keyboard input, ai- internal ai logic, shell forward

        //check if imagesq have gone off the screen

        //check collision with tanks, shells

        //ask gameSatate  -- any new shells to draw ****
        //if so call addDrawableEntity

        //ask gameSatate  -- any new shells to remove
        //if so call removeDrawableEntity

        for(Entity entity: gameState.getEntities()){
            runGameView.setDrawableEntityLocationAndAngle(entity.getId(),
                    entity.getX(),
                    entity.getY(),
                    entity.getAngle());

        }
//        gameState.shells.clear();
        return true;
    }

    public static void main(String[] args) {

        System.out.println("hello!!\n");
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
        System.out.println("Good Bye\n");
    }
}
