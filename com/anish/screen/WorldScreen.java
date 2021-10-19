package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.Floor;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;
import maze.MazeAutoRouter;
import maze.MazeGenerator;
public class WorldScreen implements Screen {

    private World world;
    private int[][] maze;
    private Calabash bro;
    private MazeAutoRouter autoRouter;
    private boolean autoMove = false;
    private int counter = 0;

    public WorldScreen() {
        MazeGenerator mazeGenerator = new MazeGenerator(World.WIDTH);
        mazeGenerator.generateMaze();
        maze = mazeGenerator.getIntMaze();
        world = new World(maze);

        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        bro = new Calabash(new Color(r,g,b), 0, world);
        world.put(bro,0,0);
        autoRouter = new MazeAutoRouter(maze, bro,world);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        int keyCode = key.getKeyCode();
        int x = bro.getX();
        int y = bro.getY();
        Boolean moveSuccessfully = false;
        Boolean reachExit = false;
        int curX = x,curY = y;
        switch(keyCode){
            case KeyEvent.VK_LEFT:
                moveSuccessfully = bro.moveTo(x-1,y);
                curX = x - 1;
                autoMove = false;
                break;
            case KeyEvent.VK_RIGHT:
                moveSuccessfully = bro.moveTo(x+1, y);
                curX = x + 1;
                autoMove = false;
                break;
            case KeyEvent.VK_UP:
                moveSuccessfully = bro.moveTo(x, y-1);
                curY = y - 1;
                autoMove = false;
                break;
            case KeyEvent.VK_DOWN:
                moveSuccessfully = bro.moveTo(x, y+1);
                curY = y + 1;
                autoMove = false;
                break;   
            case KeyEvent.VK_ENTER:
                if(autoMove == false){
                    autoMove = true;
                    autoRouter.startAutoDrive();
                }
                reachExit = autoRouter.execute();
                counter++;
        }
        if(moveSuccessfully){
            world.put(new Floor(world),x,y);
            counter++;
        }
        if(curX == World.WIDTH && curY == World.HEIGHT)reachExit = true;
        if(reachExit){
            return new WinScreen(counter);
        }
        return this;
    }
}
