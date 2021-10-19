package com.anish.screen;

import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class StartScreen extends RestartScreen{

    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("_      ____  ____  _____",6,10);
        terminal.write("/ \\__/|/  _ \\/_   \\/  __/",5,11);
        terminal.write("| |\\/||| / \\| /   /|  \\",5 ,12);
        terminal.write("| |  ||| |-||/   /_|  /_",5,13);
        terminal.write("\\_/  \\|\\_/ \\|\\____/\\____\\",5,14);
        terminal.writeCenter("This is start screen...", 30);
        terminal.writeCenter("Press ENTER to start...", 31);
        terminal = new AsciiPanel(World.WIDTH,World.HEIGHT);
    }
    
}
