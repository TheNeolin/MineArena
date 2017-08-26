package com.seitzsoftware.Player;

import android.graphics.Color;
import android.graphics.Typeface;

import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.UIButton;

import java.util.Arrays;
import java.util.List;

/**
 * Created by vette on 6/20/2017.
 */

public class Forge {

    public Bar barSlot1,barSlot2,barSlot3,barSlot4,barSlot5,barSlot6,barSlot7,barSlot8,barSlot9;
    public List<Bar> barSlots;
    public int[] currentForgePattern = new int[9];

    public Forge(){

        loadBars();
        loadBarSlots();

    }

    //public void forgePattern(bar)

    public void forgeResult(){
        loadForgePattern();
        int countAmountInForge = 0;
        String armorPattern;
        Armor newArmor;
        for (Bar b : getBarSlots()){
            if(b.amountInForge==1){
                countAmountInForge++;
            }
        }
        for (Bar b : GameMainActivity.N.getBars()){
            if (b.amountInForge == countAmountInForge){
                for (Armor a : GameMainActivity.N.getArmor()){
                    if(Arrays.equals(a.forgePattern,currentForgePattern)){
                        if(b.getBarTier() - a.getArmorTier() >= 1){
                            a.material = b.associatedOreName;
                            GameMainActivity.dbHandler.UpdateItemAmount(GameMainActivity.getUserName(),b.dBColumn.getObjDBColumnName(),-b.amountInForge);
                            GameMainActivity.dbHandler.UpdateItemString(GameMainActivity.getUserName(),a.dBColumn.getObjDBColumnName(),a.material);
                            System.out.println("You created " + a.material + " " + a.dBColumn.getObjDBColumnName());
                        }else{
                            System.out.println("That forged item is not an upgrade!");
                        }

                    }
                }
            }
        }
        loadBarSlots();
        GameMainActivity.N.loadBarAmounts();
        GameMainActivity.N.loadArmorItems();
    }

    public void loadBars(){
        int left = 100;
        int top = 100;
        int right = 140;
        int bottom = 120;
        for (Bar b : GameMainActivity.N.getBars()){
            b.uiButton = new UIButton(left,top,right,bottom,b.getOreImg(),b.getOreImg());
            top += 30;
            bottom += 30;
        }
    }

    public void loadBarSlots(){
        barSlot1 = new Bar();
        barSlot2 = new Bar();
        barSlot3 = new Bar();
        barSlot4 = new Bar();
        barSlot5 = new Bar();
        barSlot6 = new Bar();
        barSlot7 = new Bar();
        barSlot8 = new Bar();
        barSlot9 = new Bar();
        int left = 200;
        int top = 200;
        int right = 250;
        int bottom = 250;
        for (Bar b : getBarSlots()){
            b.uiButton = new UIButton(left,top,right,bottom,Assets.anvil_button,Assets.anvil_button);
            b.amountInForge = 0;
            if(left == 300){
                left = 200;
                right = 250;
                top = top + 50;
                bottom = bottom + 50;
            }else{
                left = left + 50;
                right = right + 50;
            }
        }
    }

    public void loadForgePattern(){
        int barSlotNumber = 0;
        for(Bar b : getBarSlots()){
            currentForgePattern[barSlotNumber] = b.amountInForge;
            barSlotNumber++;
        }
    }

    public List<Bar> getBarSlots() {
        barSlots = Arrays.asList(barSlot1,barSlot2,barSlot3,barSlot4,barSlot5,barSlot6,barSlot7,barSlot8,barSlot9);
        return barSlots;
    }

}
