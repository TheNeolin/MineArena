package com.seitzsoftware.Player;

import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;

/**
 * Created by vette on 6/18/2017.
 */

public class Furnace {
    public String furnaceStatus;
    public Ore oreInSlot1;
    public Ore oreInSlot2;
    public int oreAmountInSlot1;
    public int oreAmountInSlot2;
    public int numberBarsCreated;

    public Furnace(){
        furnaceStatus = "off";
        oreAmountInSlot1 = 0;
        oreAmountInSlot2 = 0;
        oreInSlot1 = null;
        oreInSlot2 = null;
    }


    public void meltResult(Ore o){
        numberBarsCreated = 0;
        while (oreAmountInSlot1 >= o.coalNeededForBar && oreAmountInSlot2 >= 1) {
            try {
                furnaceStatus = "on";
                oreAmountInSlot1 = oreAmountInSlot1 - o.coalNeededForBar;
                oreAmountInSlot2--;
                Thread.sleep(o.coalNeededForBar * 1000);
                numberBarsCreated++;
                GameMainActivity.dbHandler.UpdateItemAmount(GameMainActivity.getUserName(),o.dBColumn.getObjDBColumnName(),-1);
                GameMainActivity.dbHandler.UpdateItemAmount(GameMainActivity.getUserName(),GameMainActivity.N.coal_Ore.dBColumn.getObjDBColumnName(),-o.coalNeededForBar);
                for (Bar b : GameMainActivity.N.getBars()){
                    if(b.associatedOreName == o.oreName){
                        GameMainActivity.dbHandler.UpdateItemAmount(GameMainActivity.getUserName(),b.dBColumn.getObjDBColumnName(),1);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        GameMainActivity.N.coal_Ore.characterAmount =+ oreAmountInSlot1;
        o.characterAmount =+ oreAmountInSlot2;
        furnaceStatus = "off";
        oreInSlot1 = null;
        oreInSlot2 = null;
        oreAmountInSlot1 = 0;
        oreAmountInSlot2 = 0;
        GameMainActivity.N.loadOreAmounts();
    }

}
