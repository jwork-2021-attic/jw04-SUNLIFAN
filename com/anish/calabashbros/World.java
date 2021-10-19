package com.anish.calabashbros;

public class World {

    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;

    private Tile<Thing>[][] tiles;
    private int[][] maze;

    public World(int[][] maze) {
        this.maze = maze;
        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                switch(maze[i][j]){
                    case 0:
                        tiles[i][j].setThing(new Wall(this));
                        break;
                    case 1:
                        tiles[i][j].setThing(new Floor(this));
                        break;
                }
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        if(!isReachable(x, y))return;
        this.tiles[x][y].setThing(t);
    }

    private Boolean isReachable(int x,int y){
        if(x < 0 || y < 0 || x >=WIDTH || y >= HEIGHT)return false;
        if(get(x, y) instanceof Wall)return false;
        return true;
    }
}
