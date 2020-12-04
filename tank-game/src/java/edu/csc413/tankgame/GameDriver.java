package edu.csc413.tankgame;

import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.view.StartMenuView;

import java.awt.event.ActionEvent;

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

//    private  PrintListener listener;
    private ActionEvent event;
    private StartMenuView startMenuView;
    private GameKeyListener gameKeyListener ;

    public GameDriver() {

        mainView = new MainView();
        runGameView = mainView.getRunGameView();
        gameState = new GameState();
//        listener = new PrintListener();
//        gameKeyListener = new GameKeyListener();

    }


    public void start() {

//        event.getActionCommand();
        // TODO: Implement.
        // This should set the MainView's screen to
        // the start menu screen.
//        listener.actionPerformed();

//        mainView.getRunGameView();
//        String command = event.getActionCommand();


        mainView.setScreen(MainView.Screen.START_MENU_SCREEN);
//        runGameView;
//        listener.actionPerformed();
//        if
//        mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);

        runGame();
    }

//    private double x = 200.0;

    public static PlayerTank playerTank;
    public void runGame() {

       playerTank = new PlayerTank(
                GameState.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_INITIAL_X,
//                x,
                RunGameView.PLAYER_TANK_INITIAL_Y,
                RunGameView.PLAYER_TANK_INITIAL_ANGLE
        );
//        AiTank ai_tank = new AiTank(
        AiTank ai_tank = new AiTank(
                GameState.AI_TANK_ID,
                RunGameView.AI_TANK_INITIAL_X,
                RunGameView.AI_TANK_INITIAL_Y,
                RunGameView.AI_TANK_INITIAL_ANGLE
        );

        gameState.addEntities(playerTank);
        gameState.addEntities(ai_tank);

        runGameView.addDrawableEntity(GameState.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_IMAGE_FILE,
                playerTank.getX(), playerTank.getY(), playerTank.getAngle()
        );
        runGameView.addDrawableEntity(GameState.AI_TANK_ID,
                RunGameView.AI_TANK_IMAGE_FILE,
                ai_tank.getX(), ai_tank.getY(), ai_tank.getAngle()
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
//        x++;
//        runGameView.setDrawableEntityLocationAndAngle(
//                GameState.PLAYER_TANK_ID,
//                x,
//                300,
//                Math.toRadians(45));
        for(Entity entity: gameState.getEntities()){
            entity.move(gameState);
        }


        //player on keyboard input, ai- internal ai logic, shell forward
//        for(Entity entity: gameState.getEntities()){
//            gameState.upPressed(true);
//            entity.move(gameState);
//            update();
//        }

        //check if images have gone off the screen

        //check collision with tanks, shells

        //ask gameSatate  -- any new shells to draw
        //if so call addDrawableEntity

        //ask gameSatate  -- any new shells to remove
        //if so call removeDrawableEntity

        for(Entity entity: gameState.getEntities()){
            runGameView.setDrawableEntityLocationAndAngle(entity.getId(),
                    entity.getX(),
                    entity.getY(),
                    entity.getAngle());
        }

//        for(everything in game that is drawn)
//        runGameView.setDrawableEntityLocationAndAngle(..);
//        x++;
//        runGameView.setDrawableEntityLocationAndAngle(GameState.PLAYER_TANK_ID
//                , x, 300.0, Math.toRadians(45.0));
        return true;
    }

    public static void main(String[] args) {

        System.out.println("hello!!\n");
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
        System.out.println("Good Bye\n");
    }
}
