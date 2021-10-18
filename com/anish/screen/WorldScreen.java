package com.anish.screen;

import java.awt.event.KeyEvent;

import com.anish.Monsters.Monster;
import com.anish.Monsters.MyUtils;
import com.anish.Monsters.QuickSorter;
import com.anish.Monsters.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Monster[][] monsters;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();
        int row = 16,col = 16;

        monsters = MyUtils.generateMonsters(world);
        
        MyUtils.putCreaturesInPlace(monsters, world, row, col, 2, 20, 2);

        QuickSorter<Monster> b = new QuickSorter<>(Monster.class);
        b.load(monsters);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[][] monsters, String step) {
        String[] couple = step.split("<->");
        getMonsterByRank(monsters, Integer.parseInt(couple[0])).swap(getMonsterByRank(monsters, Integer.parseInt(couple[1])));
    }

    private Monster getMonsterByRank(Monster[][] monsters, int rank) {
        for(int i = 0; i < monsters.length;i++)
            for(int j = 0; j < monsters[0].length;j++){
                if(monsters[i][j].getRank() == rank)return monsters[i][j];
            }
        return null;
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

        if (i < this.sortSteps.length) {
            this.execute(monsters, sortSteps[i]);
            i++;
        }

        return this;
    }

}
