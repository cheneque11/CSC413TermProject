package edu.csc413.tankgame;

//import edu.csc413.tankgame.view.PrintListener;

import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;
import edu.csc413.tankgame.view.StartMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GameDriver is the primary controller class for the tank game. The game is launched from GameDriver.main, and
 * GameDriver is responsible for running the game loop while coordinating the views and the data models.
 */

public class GameDriver {


    // TODO: Implement.
    // Add the instance variables, constructors,
    // and other methods needed for this class. GameDriver is the centerpiece
    // for the tank game, and should store and manage the
    // other components (i.e. the views and the models). It also is
    // responsible for running the game loop.
    public static RunGameView runGameView;
    public static GameState gameState;
    public static MainView mainView;
    public static boolean gameOver = true;

    static String shId;


    public GameDriver() {

        mainView = new MainView();
        runGameView = mainView.getRunGameView();
        gameState = new GameState();
    }

//    public static void actionPerformed(ActionEvent event){
//        String actionCommand = event.getActionCommand();
//        GameDriver gameDriver = new GameDriver();
//        switch (actionCommand){
//            case "start_ac": gameDriver.mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
//            case "exit_ac" : gameDriver.mainView.closeGame();
//            break;
//        }
//    }

    public void start() {


        // TODO: Implement.
        // This should set the MainView's screen to
        // the start menu screen.

        mainView.setScreen(MainView.Screen.START_MENU_SCREEN);
//        actionPerformed();
//
        runGame();
    }

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
                RunGameView.AI_TANK_2_INITIAL_X,
                RunGameView.AI_TANK_2_INITIAL_Y,
                RunGameView.AI_TANK_2_INITIAL_ANGLE
        );

        Walls.getWalls();

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
                ai_tank.getX() + 100, ai_tank.getY() - 100, ai_tank.getAngle()
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
    static int i = 0;

    private boolean update() {
        //every 8ms ask tanks, shells to move


        for (Entity entity : gameState.getEntities()) {
            entity.move(gameState);
        }

        for (Entity entity : gameState.getShells()) {
            gameState.addEntities(entity);
            runGameView.addDrawableEntity(
                    entity.getId(),
                    RunGameView.SHELL_IMAGE_FILE,
                    entity.getX(),
                    entity.getY(),
                    entity.getAngle()
            );
        }

        if (i < 1) {
            System.out.println("INSEIDE PLAYERTANK GETLIFE");
            playerTank.getLife();
            secondAi.getLife();
            ai_tank.getLife();
            for (Entity entity : gameState.getGreenLives()) {
                gameState.addEntities(entity);
                runGameView.addDrawableEntity(
                        entity.getId(),
                        RunGameView.GREEN_IMAGE_FILE,
                        entity.getX(),
                        entity.getY(),
                        entity.getAngle()
                );
            }
            for (Entity entity : gameState.getRedLives()) {
                gameState.addEntities(entity);
                runGameView.addDrawableEntity(
                        entity.getId(),
                        RunGameView.RED_IMAGE_FILE,
                        entity.getX(),
                        entity.getY(),
                        entity.getAngle()
                );
            }
            for (Entity entity : gameState.getBlueLives()) {
                gameState.addEntities(entity);
                runGameView.addDrawableEntity(
                        entity.getId(),
                        RunGameView.BLUE_IMAGE_FILE,
                        entity.getX(),
                        entity.getY(),
                        entity.getAngle()
                );
            }
            i++;
        }
        //ask gameSatate  -- any new shells to remove
        //if so call removeDrawableEntity

        /* CHECK FOR SHELL ON SHELL COLLISION */
        for (int i = 1; i < gameState.getEntities().size(); i++) {
            for (int j = 0; j < gameState.getEntities().size(); j++) {
                handleCollision(gameState.getEntities().get(i),
                        gameState.getEntities().get(j));
            }
        }

        /* FOR EACH LOOP FINDS ANY ENTITY THAT IS A WALL FOR COLLISION HANDLING */
        for (Entity e : gameState.getEntities()) {
            if (e.getId() != "player-tank"
                    && e.getId() != "ai-tank"
                    && e.getId() != "second-ai-tank"
                    && e.getClass() != Shell.class) {
//                    System.out.println(e.getClass());
                handleCollision(e, playerTank);
                handleCollision(e, ai_tank);
                handleCollision(e, secondAi);
                handleCollision(playerTank, e);
                handleCollision(ai_tank, e);
                handleCollision(secondAi, e);
            }
        }

        gameState.clearShells();

        /* SHELLS GETTING CLOSE TO SCREEN EDGE REMOVED */
        for (Entity e : gameState.getEntities()) {
            if (e.getX() < GameState.SHELL_X_LOWER_BOUND + 50 ||
                    e.getX() > GameState.TANK_X_UPPER_BOUND - 50 ||
                    e.getY() > GameState.SHELL_Y_UPPER_BOUND - 50 ||
                    e.getY() < GameState.SHELL_Y_LOWER_BOUND + 50) {

//                System.out.println("ADDING TO ADDrEMOVEDSHELLS\n");
                if (playerTank.getId() != e.getId() && ai_tank.getId() != e.getId()
                        && secondAi.getId() != e.getId()) {
                    gameState.addRemoveShellsList(e);
                }
            }
        }
        for (Entity e : gameState.removeShellsList) {
            gameState.removeEntity(e);
            runGameView.removeDrawableEntity(e.getId());
        }

        //check collision with tanks, shells
        handleCollision(playerTank, ai_tank);
        handleCollision(playerTank, secondAi);
        handleCollision(ai_tank, secondAi);

        //ask gameSatate  -- any new shells to draw ****
        //if so call addDrawableEntity

        for (Entity entity : gameState.getEntities()) {
            runGameView.setDrawableEntityLocationAndAngle(entity.getId(),
                    entity.getX(),
                    entity.getY(),
                    entity.getAngle());
        }
//        gameState.shells.clear();

        if (!gameOver) {

            gameState.clearEverything();

            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
            return gameOver;
        }
        return gameOver;
    }

    public static void main(String[] args) {

        System.out.println("hello!!\n");
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();
        System.out.println("Good Bye\n");
    }


    /* DO ENTITIES OVERLAP FUNCTION */
    private boolean entitiesOverlap(Entity entity1, Entity entity2){

        return entity1.getX() < entity2.getXBound()
                && entity1.getXBound() > entity2.getX()
                && entity1.getY() < entity2.getYBound()
                && entity1.getYBound() > entity2.getY();
    }

    /* WINNER FUNCTION IF PLAYES LIFE IS 0 THAN LOSE IF BOTH AIS LIVES ARE 0 PLAYER WINS */
    private void winner() {
//        System.out.println("player" + playerTank.healthDecrease() +
//                "ai" + ai_tank.healthDecrease() + "cush" + cushionAiTank.healthDecrease());
        if (playerTank.healthDecrease() <= 0) {
            System.out.println("YOU LOSE!!!\n");
            runGameView.addDrawableEntity(
                    GameState.YOULOSE_ID,
                    RunGameView.YOU_LOSE_IMAGE_FILE,
                    RunGameView.YOU_LOSE_INITIAL_X,
                    RunGameView.YOU_LOSE_INITIAL_Y,
                    0
            );
//            gameOver = false;
            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
        } else if (ai_tank.healthDecrease() <= 0 && secondAi.healthDecrease() <= 0) {
            System.out.println("YOU WIN!!!\n");
            runGameView.addDrawableEntity(
                    GameState.YOUWIN_ID,
                    RunGameView.YOU_WIN_IMAGE_FILE,
                    RunGameView.YOU_WIN_INITIAL_X,
                    RunGameView.YOU_WIN_INITIAL_Y,
                    0
            );


//            gameOver = false;
            mainView.setScreen(MainView.Screen.END_MENU_SCREEN);
        }
//        gameOver = false;
    }

    /* HANDLE COLLISION FUNCTIO FOR ALL ENTITIES */
    private void handleCollision(Entity entity1, Entity entity2) {

//        System.out.println("Entity: " + entity1.getClass() +
//                " Entity2: " +entity2.getClass());
        double mLeft = entity1.getXBound() - entity2.getX();
        double mRight = entity1.getXBound() - entity2.getX();
        double mDown = entity1.getYBound() - entity2.getY();
        double mUp = entity1.getYBound() - entity2.getY();
        double min = 0;
        /* TANK ON TANK COLLISION */
        if (entity1 instanceof Tank && entity2 instanceof Tank) {
            // Handle tank tank collision
            if(entitiesOverlap(entity1, entity2)){

                min = Math.min(Math.min(Math.min(mLeft, mRight), mDown),mUp);

                if(min == mLeft) {
                    entity1.setX(entity1.getX() - min / 2);
                    entity2.setX(entity2.getX() + min / 2);
                }else if(min == mRight) {
                    entity1.setX(entity1.getX() + min / 2);
                    entity2.setX(entity2.getX() - min / 2);
                }else if(min == mDown) {
                    entity1.setY(entity1.getY() + min / 2);
                    entity2.setY(entity2.getY() - min / 2);
                }else if(min == mUp) {
                    entity1.setY(entity1.getY() - min / 2);
                    entity2.setY(entity2.getY() + min / 2);
                }
            }
            /* TANK ON SHELL COLLISION */
        } else if (entity1 instanceof Tank && entity2 instanceof Shell) {
//             Handle tank shell collision
            /* CHECK FOR OVERLAP */
            if(entitiesOverlap(entity1, entity2)){
//                System.out.println("SHELL OVERLAPPING WITH TANK 1");
                if (entity1.getX() < entity2.getXBound()) {

                    if(entity1.getId() != ((Shell) entity2).getTankId()) {
                        runGameView.removeDrawableEntity(entity2.getId());
                        gameState.removeEntity(entity2);
                        runGameView.addAnimation(RunGameView.BIG_EXPLOSION_ANIMATION,
                                RunGameView.BIG_EXPLOSION_FRAME_DELAY,
                                entity2.getX(),
                                entity2.getY());
                        ((Tank) entity1).healthDecrease();

                        if(((Tank) entity1).healthDecrease() == 0) {
                            winner();
                            gameState.removeEntity(entity1);
                            runGameView.removeDrawableEntity(entity1.getId());

//                            winner();
//                                mainView.setScreen(MainView.Screen.END_MENU_SCREEN);

                            /* REMOVE GREEN ENTITY SQUARE REPRESENTING PLAYER LIFE */
                            if (entity1.getId() == "player-tank") {
                                gameState.removeEntity(gameState.greenLives.get(0));
                                runGameView.removeDrawableEntity(GameState.GREEN_ID);
                                runGameView.addDrawableEntity(
                                        GameState.YOULOSE_ID,
                                        RunGameView.YOU_LOSE_IMAGE_FILE,
                                        RunGameView.YOU_LOSE_INITIAL_X,
                                        RunGameView.YOU_LOSE_INITIAL_Y,
                                        0
                                );
                            }
                            if (entity1.getId() == "ai-tank") {
                                gameState.removeEntity(gameState.redLives.remove(0));
                                runGameView.removeDrawableEntity(GameState.RED_ID);
                            }
                            if (entity1.getId() == "second-ai-tank") {
                                gameState.removeEntity(gameState.blueLives.get(0));
                                runGameView.removeDrawableEntity(GameState.BLUE_ID);
                            }
//                            winner();
                        }

                    }
                }
            }               /* SHELL ON TAMNK COLLISION */
        }
        else if (entity1 instanceof Shell && entity2 instanceof Tank) {
//             Same as above
            /* CHECK FOR OVERLAP */
            if(entitiesOverlap(entity1, entity2)){
//                System.out.println("SHELL COLLIDING WITH TANK 1");
                if (entity1.getX() < entity2.getXBound()  ) {
                    if (((Shell) entity1).getTankId() != entity2.getId()) {

//                            System.out.println("SHELL COLLIDING WITH TANK 2");

//                            System.out.println("SHELL " + entity1.getTankId() +
//                                            " COLLIDING WITH TANK " + entity2.getTankId());
                        System.out.println(((Tank) entity2).healthDecrease());
                        runGameView.removeDrawableEntity(entity1.getId());
                        gameState.removeEntity(entity1);
                        runGameView.addAnimation(RunGameView.BIG_EXPLOSION_ANIMATION,
                                RunGameView.BIG_EXPLOSION_FRAME_DELAY,
                                entity2.getX(),
                                entity2.getY());
                        ((Tank) entity2).healthDecrease();
                        System.out.println(((Tank) entity2).healthDecrease());
                        if(((Tank) entity2).healthDecrease() == 0){
                            System.out.println("DELETING TANK!!!!");
                            winner();
                            gameState.getEntities().clear();
                            gameState.removeEntity(entity2);
                            runGameView.removeDrawableEntity(entity2.getId());

                        }
                    }
                }
            }           /* SHELL ON SHELL COLLISION */
        }
        else if (entity1 instanceof Shell && entity2 instanceof Shell) {
            /* CHECK FOR OVERLAP */
            if(entitiesOverlap(entity1, entity2)){

                if(entity1.getX() < entity2.getX()) {
                    gameState.removeEntity(entity1);
                    gameState.removeEntity(entity2);
                    runGameView.removeDrawableEntity(entity1.getId());
                    runGameView.removeDrawableEntity(entity2.getId());
                    runGameView.addAnimation(RunGameView.SHELL_EXPLOSION_ANIMATION,
                            RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                            entity2.getX(),
                            entity2.getY());
                }
            }           /* WALL ON SHELL */
        }
        else if (entity1 instanceof Walls && entity2 instanceof Shell) {
//                   System.out.println("SHELL COLLIDING WITH WALL 1");
            if(entitiesOverlap(entity1, entity2)) {
                if (entity1.getX() < entity2.getX())
                {

                    gameState.removeEntity(entity2);
                    runGameView.removeDrawableEntity(entity2.getId());
                    runGameView.addAnimation(RunGameView.SHELL_EXPLOSION_ANIMATION,
                            RunGameView.SHELL_EXPLOSION_FRAME_DELAY,
                            entity2.getX(),
                            entity2.getY());
                    ((Walls) entity1).healthDecrease();
                    if(((Walls) entity1).healthDecrease() == 0){

                        gameState.removeEntity(entity1);
                        runGameView.removeDrawableEntity(entity1.getId());
                    }
                }
            }
            /* SHELL ON WALL */
        }else if (entity1 instanceof Shell && entity2 instanceof Walls){
//                    System.out.println("SHELLS COLLIDING WITH WALL 2");

//                    if(entitiesOverlap(entity1, entity2)) {
//                        if (entity1.getX() < entity2.getX())
//                        gameState.removeEntity(entity1);
//                        runGameView.removeDrawableEntity(entity1.getId());
//
//                    }

            /* TANK AND WALLS COLLISION */
        }else if (entity1 instanceof Tank && entity2 instanceof Walls) {
            if(entitiesOverlap(entity1, entity2)) {

                min = Math.min(Math.min(Math.min(mLeft, mRight), mDown), mUp);

                if (min == mLeft) {
                    entity1.setX(entity1.getX() - min);
                } else if (min == mRight) {
                    entity1.setX(entity1.getX() + min);
                } else if (min == mDown) {
                    entity1.setY(entity1.getY() + min);
                } else if (min == mUp) {
                    entity1.setY(entity1.getY() - min);
                }
            }
            /* WALLS ON TANK COLLISON */
        }else if (entity1 instanceof Walls && entity2 instanceof Tank){
//                        System.out.println("WALLS COLLIDING WITH TANK 1");

            if(entitiesOverlap(entity1, entity2)) {
//                            System.out.println("WALLS OVERLAPPING WITH TANK 1");
                min = Math.min(Math.min(Math.min(mLeft, mRight), mDown),mUp);

                if(min == mLeft) {
                    entity2.setX(entity2.getX() + min);
                }else if(min == mRight) {
                    entity2.setX(entity2.getX() - min);
                }else if(min == mDown) {
                    entity2.setY(entity2.getY() - min);
                }else if(min == mUp) {
                    entity2.setY(entity2.getY() + min);
                }
            }
        }
    }

    public static class PrintListener implements ActionListener {

        //    StartMenuView startMenuView;
        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();
            if (actionCommand.equals(StartMenuView.START_BUTTON_ACTION_COMMAND)) {

//                System.out.println("Start button pressed\n");
                mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);

                //runGame();
//                mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
            }
            else if (actionCommand.equals((StartMenuView.EXIT_BUTTON_ACTION_COMMAND))) {
                System.out.println("Exit button pressed\n");
                mainView.closeGame();

            }
            else if (actionCommand.equals((StartMenuView.RESTART_BUTTON_ACTION_COMMAND))) {

                System.out.println("INSIDE RESET\n");
                runGameView.reset();
                mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);

            }
        }
    }

}