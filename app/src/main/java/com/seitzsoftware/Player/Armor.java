package com.seitzsoftware.Player;

import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.database.DBColumn;

import java.lang.reflect.Array;

/**
 * Created by vette on 6/20/2017.
 */

public class Armor extends InventoryItem {

    //int durability;
    int strengthValue;
    int staminaValue;
    public int materialTier;
    public String material;
    public int[] forgePattern = new int[9];

    public Armor(float x, float y, int width, int height) {
        super(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Armor(String armorName, int b1, int b2, int b3, int b4, int b5, int b6, int b7, int b8, int b9){
        this.dBColumn = new DBColumn(armorName, "armor_" + armorName, "TEXT");
        this.imageString = "armor_" + armorName + ".png";
        this.forgePattern = new int[]{b1,b2,b3,b4,b5,b6,b7,b8,b9};
    }

    public void loadArmorAttributes(){
        for (Ore o : GameMainActivity.N.getOres()){
            if (o.oreName.equals(this.material)){
                strengthValue = o.getOreMineValue()/100;
                staminaValue = o.getOreMineValue()/50;
            }
        }
    }

    public int getArmorTier(){
        int armorTier = 0;
        int armorTierLoop = 0;
        for (Bar b : GameMainActivity.N.getBars()){
            armorTierLoop++;
            if (b.associatedOreName.equals(this.material)){
                System.out.println("Matched to " + this.material);
                armorTier = armorTierLoop;
            }
        }
        return  armorTier;
    }
}
