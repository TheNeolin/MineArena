package com.seitzsoftware.Player;

import android.graphics.Bitmap;

import com.seitzsoftware.database.DBColumn;
import com.seitzsoftware.framework.util.UIButton;

/**
 * Created by vette on 6/18/2017.
 */

public class InventoryItem {

    public float x, y;
    public int width, height;


    public int characterAmount;

    public UIButton uiButton;

    public DBColumn dBColumn;
    public String imageString;
    public Bitmap imageBitmap;


    public InventoryItem(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public InventoryItem(String itemImg,String itemName,String columnName){
        this.dBColumn = new DBColumn(itemName, columnName, "INT");
        this.imageString = itemImg;
    }

    public InventoryItem(){

    }


    public void update() {

    }

    public void setXY(float x, float y){
        this.x = x;
        this.y = y;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Bitmap getOreImg() {
        return imageBitmap;
    }
}