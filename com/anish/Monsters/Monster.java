package com.anish.Monsters;

import java.awt.Color;

public class Monster extends Creature implements Comparable<Monster>{
    private int rank;
    public Monster(Color color, World world) {
        super(color, (char) 2, world);
        this.rank = color.getRed();
        }

    
    public int getRank(){return this.rank;}

    @Override
    public int compareTo(Monster o) {
        return Integer.valueOf(this.rank).compareTo(o.getRank());
    }

    @Override
    public String toString(){
        return String.valueOf(this.rank);
    }

    public void swap(Monster another){
        int x = this.getX();
        int y = this.getY();
        this.moveTo(another.getX(), another.getY());
        another.moveTo(x, y);
    }
}
