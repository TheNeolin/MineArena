package com.seitzsoftware.Player;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.database.DBColumn;
import com.seitzsoftware.framework.util.RandomNumberGenerator;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vette on 6/11/2017.
 */

public class Ore extends InventoryItem {

    public int amountInFurnace;
    public int amountInFurnaceQueue;
    public int coalNeededForBar;
    public int oreMineValue;
    public String oreName;

    public Ore(float x, float y, int width, int height) {
        super(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Ore(String oreImg,String oreName,String columnName,int oreValue, int coalNeededForBar){
        super(oreImg,oreName,columnName);
        this.dBColumn = new DBColumn(oreName, columnName, "INT");
        this.oreMineValue = oreValue;
        this.imageString = oreImg;
        this.coalNeededForBar = coalNeededForBar;
        this.oreName = oreName;
    }

    public Ore(){

    }

    public void update() {

    }



    public int getAmountInFurnace() {
        return amountInFurnace;
    }

    public void setAmountInFurnace(int amountInFurnace) {
        this.amountInFurnace = amountInFurnace;
    }

    public int getAmountInFurnaceQueue() {
        return amountInFurnaceQueue;
    }

    public void setAmountInFurnaceQueue(int amountInFurnaceQueue) {
        this.amountInFurnaceQueue = amountInFurnaceQueue;
    }

    public int getOreMineValue() {
        return oreMineValue;
    }

}