package edu.csc413.tankgame;

import edu.csc413.tankgame.model.Entity;
import edu.csc413.tankgame.model.GameState;
import edu.csc413.tankgame.model.PlayerTank;
import edu.csc413.tankgame.model.Tank;
import edu.csc413.tankgame.view.RunGameView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent event){
        //not useful
    }
    @Override
    public void keyPressed(KeyEvent event){

        int keyCode = event.getKeyCode();
        if(keyCode == KeyEvent.VK_W){

            System.out.println("W pressed(Up)\n");

//            GameDriver.mainView.closeGame();
        }
        if(keyCode == KeyEvent.VK_S){

            System.out.println("S pressed(Down)\n");
//            gameState.downPressed();
        }
        if(keyCode == KeyEvent.VK_Q){

            System.out.println("Q pressed(left)\n");
//            gameState.leftPressed();
        }
        if(keyCode == KeyEvent.VK_E){

            System.out.println("E pressed(Right)\n");
//            gameState.downPressed();
        }

        if(keyCode == KeyEvent.VK_ESCAPE){
            System.out.println("esc pressed\n");
        }
    }
    @Override
    public void keyReleased(KeyEvent event){

        int keyCode = event.getKeyCode();
        if(keyCode == KeyEvent.VK_W){
            System.out.println("w released\n");
//            gameState.upPressed(false);
        }else if(keyCode == KeyEvent.VK_ESCAPE){
            System.out.println("esc released\n");
        }
        if(keyCode == KeyEvent.VK_S){
            System.out.println("s released\n");
        }
        if(keyCode == KeyEvent.VK_Q){
            System.out.println("q released\n");
        }
        if(keyCode == KeyEvent.VK_E){
            System.out.println("e released\n");
        }

    }
}
