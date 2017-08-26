package com.seitzsoftware.Player;

import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.database.DBColumn;

/**
 * Created by vette on 6/20/2017.
 */

public class Bar extends InventoryItem {

    public String associatedOreName;
    public int amountInForge;


    public Bar(float x, float y, int width, int height) {
        super(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Bar(String barImg,String barName,String columnName,String associatedOreName){
        super(barImg,barName,columnName);
        this.dBColumn = new DBColumn(barName, columnName, "INT");
        this.imageString = barImg;
        this.associatedOreName = associatedOreName;
    }

    public Bar(){

    }

    public int getBarTier(){
        int barTier = 0;
        int barTierLoop = 0;
        for (Bar b : GameMainActivity.N.getBars()){
            barTierLoop++;
            if (b.associatedOreName == this.associatedOreName){
                barTier = barTierLoop;
            }
        }
        return  barTier;
    }
}
