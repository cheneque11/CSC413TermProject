package edu.csc413.tankgame;

import edu.csc413.tankgame.model.PlayerTank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    private final int up;
    private final int down;
    private final int left;
    private final int right;
    private PlayerTank tank;

    public GameKeyListener(int up, int down, int left, int right, PlayerTank tank) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.tank = tank;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    double x;
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){

            GameDriver.playerTank.setUpPressed(true);
        }
        if(key == KeyEvent.VK_DOWN){
            GameDriver.playerTank.setDownPressed(true);

        }
        if(key == KeyEvent.VK_LEFT){
            GameDriver.playerTank.setLeftPressed(true);

        }
        if(key == KeyEvent.VK_RIGHT){
            GameDriver.playerTank.setRightPressed(true);

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP){
            System.out.println("inside controltank\n up\n");
            GameDriver.playerTank.setUpPressed(false);
        }
        if(key == KeyEvent.VK_DOWN){
            GameDriver.playerTank.setDownPressed(false);
        }
        if(key == KeyEvent.VK_LEFT){
            GameDriver.playerTank.setLeftPressed(false);
        }
        if(key == KeyEvent.VK_RIGHT){
            GameDriver.playerTank.setRightPressed(false);
        }

    }
}