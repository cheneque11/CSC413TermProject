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

        secondAi = new SecondAi(
                GameState.SECOND_AI_TANK_ID,
                RunGameView.AI_TANK_INITIAL_X +100,
                RunGameView.AI_TANK_INITIAL_Y,
                RunGameView.PLAYER_TANK_INITIAL_ANGLE
        );

        gameState.addEntities(ai_tank);
        gameState.addEntities(playerTank);
        gameState.addEntities(secondAi);

        Walls.addWalls();

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


        if(entitiesOverlap(playerTank, ai_tank)){
            System.out.println("inside entities overlappoing\n");
            double mLeft = playerTank.getXBound() - ai_tank.getX();
            double mRight = ai_tank.getXBound() - playerTank.getX();
            double mDown = ai_tank.getYBound() - playerTank.getY();
            double mUp = playerTank.getYBound() - ai_tank.getY();
            double min = 0;

            if(playerTank.getXBound() < ai_tank.getX()) {

//                ai_tank.setX(playerTank.getXBound() - ai_tank.getX());


                System.out.println("inside playerTank.getXBound() < ai_tank.getX()\n");
//                ai_tank.setX(ai_tank.getX()+15);
            }else if(playerTank.getX() > ai_tank.getXBound()){
//                ai_tank.setX(ai_tank.getXBound() - playerTank.getX() + 20);
                System.out.println("inside playerTank.getX() > ai_tank.getXBound()\n");
//                ai_tank.setX(ai_tank.getX()-15);


            }else if(playerTank.getYBound() < ai_tank.getY()){
//                ai_tank.setX(playerTank.getYBound() - ai_tank.getY() + 20);
//                ai_tank.setY(ai_tank.getY()+15);


            }else if(playerTank.getY() > ai_tank.getYBound()){
//                ai_tank.setX(ai_tank.getYBound() - playerTank.getY()- 20);
//                ai_tank.setY(ai_tank.getY()-15);


            }
            min = Math.min(Math.min(Math.min(mLeft, mRight), mDown),mUp);
            if(min == mLeft) {
                playerTank.setX(playerTank.getX() - min / 2);
                ai_tank.setX(ai_tank.getX() + min / 2);
            }else if(min == mRight) {
                playerTank.setX(playerTank.getX() + min / 2);
                ai_tank.setX(ai_tank.getX() - min / 2);
            }else if(min == mDown) {
                playerTank.setY(playerTank.getY() + min / 2);
                ai_tank.setY(ai_tank.getY() - min / 2);
            }else if(min == mUp) {
                playerTank.setY(playerTank.getY() - min / 2);
                ai_tank.setY(ai_tank.getY() + min / 2);
            }

        }


        if(entitiesOverlap(playerTank, secondAi)){
            System.out.println("inside entities overlappoing\n");
            if(playerTank.getX() < secondAi.getX()) {

                System.out.println("inside playerTank.getXBound() < ai_tank.getX()\n");
                secondAi.setX(secondAi.getX()+15);
            }
            if(playerTank.getX() > secondAi.getX()){

                System.out.println("inside playerTank.getX() > ai_tank.getXBound()\n");
                secondAi.setX(secondAi.getX()-15);
            }
            if(playerTank.getY() < secondAi.getY()){

                secondAi.setY(secondAi.getY()+15);
            }
            if(playerTank.getY() > secondAi.getY()){

                secondAi.setY(secondAi.getY()-15);

            }
        }




        gameState.clearShells();

        for(Entity e: gameState.getEntities()){
            if(e.getX() < GameState.SHELL_X_LOWER_BOUND + 50||
                    e.getX() > GameState.TANK_X_UPPER_BOUND - 50||
                    e.getY() > GameState.SHELL_Y_UPPER_BOUND - 50||
                    e.getY() < GameState.SHELL_Y_LOWER_BOUND + 50){

                System.out.println("ADDING TO ADDrEMOVEDSHELLS\n");
                if(playerTank.getId() != e.getId() && ai_tank.getId() != e.getId()
                        && secondAi.getId() != e.getId()) {
                    gameState.addRemoveShellsList(e);
                }
            }
        }

        for(Entity e: gameState.removeShellsList){
            gameState.removeEntity(e);
            runGameView.removeDrawableEntity(e.getId());
        }
//        gameState.clearRemovedShells();
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

    private boolean entitiesOverlap(Entity entity1, Entity entity2){

        return entity1.getX() < entity2.getXBound()
                && entity1.getXBound() > entity2.getX()
                && entity1.getY() < entity2.getYBound()
                && entity1.getYBound() > entity2.getY();
    }
}
