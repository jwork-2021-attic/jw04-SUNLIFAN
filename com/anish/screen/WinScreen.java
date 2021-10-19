package com.anish.screen;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class WinScreen implements Screen{
    private int step;
    public WinScreen(int step){
        super();
        this.step = step;
    }
    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.writeCenter("You have reach the EXIT! Awesome!", 15);
        terminal.writeCenter("You used "+step+" steps to reach the EXIT!", 16);
        terminal.writeCenter("PRESS ENTER TO REMAKE...",17);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        switch(key.getKeyCode()){
            case KeyEvent.VK_ENTER:
                return new StartScreen();
            default:
                return this;
        }
    }
    
}
