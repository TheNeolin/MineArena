package com.seitzsoftware.Player;

/**
 * Created by vette on 6/20/2017.
 */

public class Weapon extends InventoryItem {

    public Weapon(float x, float y, int width, int height) {
        super(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
