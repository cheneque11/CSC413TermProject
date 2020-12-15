package edu.csc413.tankgame;

import edu.csc413.tankgame.model.GameState;
import edu.csc413.tankgame.model.PlayerTank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    double x;
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            GameState.setUpPressed();
        }
        if(key == KeyEvent.VK_DOWN){
            GameState.setDownPressed();
        }
        if(key == KeyEvent.VK_LEFT){
            GameState.setLeftPressed();
        }
        if(key == KeyEvent.VK_RIGHT){
            GameState.setRightPressed();
        }
        if(key == KeyEvent.VK_A) {

            System.out.println("A pressed\n");
//            GameDriver.playerTank.coolDown();
            GameState.isShootPressed();
        }
        if(key == KeyEvent.VK_D) {

            System.out.println("D pressed\n");
            GameState.reset();
        }

        if(key == KeyEvent.VK_ESCAPE){
            System.out.println("esc pressed\n");
            GameState.escapePressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            System.out.println("inside controltank\n up\n");
            GameState.notUpPressed();
        }
        if(key == KeyEvent.VK_DOWN){
            GameState.notDownPressed();
        }
        if(key == KeyEvent.VK_LEFT){
            GameState.notLeftPressed();
        }
        if(key == KeyEvent.VK_RIGHT){
            GameState.notRighttPressed();
        }
        if(key == KeyEvent.VK_A){
//            System.out.println("A realsed\n");
            GameState.notShootPressed();
        }
        if(key == KeyEvent.VK_ESCAPE){
//            System.out.println("esc pressed\n");
            GameState.escapeNotPressed();
        }
    }
}