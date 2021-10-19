package com.anish.calabashbros;

import java.awt.Color;

public class Creature extends Thing {

    Creature(Color color, char glyph, World world) {
        super(color, glyph, world);
    }

    //return true if move successfully
    public Boolean moveTo(int xPos, int yPos) {
        this.world.put(this, xPos, yPos);
        if(this.getX() != xPos || this.getY() != yPos)return false;
        else return true;
    }

}
