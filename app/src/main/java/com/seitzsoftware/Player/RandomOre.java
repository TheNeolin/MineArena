package com.seitzsoftware.Player;

import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.database.DBColumn;
import com.seitzsoftware.framework.util.RandomNumberGenerator;
import com.seitzsoftware.Player.Ore;
import com.seitzsoftware.framework.util.UIButton;

/**
 * Created by vette on 6/11/2017.
 */

public class RandomOre extends Ore {

    int blockValue;
    String blockMined = "Mine!";

    public RandomOre(float x, float y, int width, int height) {
        super(x,y,width,height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public RandomOre() {
        this.blockValue = RandomNumberGenerator.getRandIntBetween(1,10000);
        loadRandomOre();
    }

    public void update() {

    }

    public void loadRandomOre(){
        int left = RandomNumberGenerator.getRandIntBetween(100, GameMainActivity.GAME_WIDTH-150);;
        int top = RandomNumberGenerator.getRandIntBetween(100,GameMainActivity.GAME_HEIGHT-150);
        int right = left + 30;
        int bottom = top + 30;
        this.uiButton = new UIButton(left,top,right,bottom, GameMainActivity.N.coal_Ore.getOreImg(),GameMainActivity.N.coal_Ore.getOreImg());

    }

    public void onClick(){;
        this.blockValue = RandomNumberGenerator.getRandIntBetween(1,10000);
        this.x = RandomNumberGenerator.getRandIntBetween(150, GameMainActivity.GAME_WIDTH-150);
        this.y = RandomNumberGenerator.getRandIntBetween(150,GameMainActivity.GAME_HEIGHT-150);
        blockMined = "You mined " + returnOreMined(blockValue).getObjDBColumnName() + "!";
        GameMainActivity.dbHandler.UpdateItemAmount(GameMainActivity.getUserName(), returnOreMined(blockValue).getObjDBColumnName(),1);
        this.loadRandomOre();
    }

    private DBColumn returnOreMined(int blockValue){
        boolean blockDiscovered = false;
        DBColumn blockMinedColumn = GameMainActivity.N.coal_Ore.dBColumn;

        while (blockDiscovered == false) {
            for (Ore o : GameMainActivity.N.getOresReverse()) {
                if(blockValue >= o.oreMineValue && blockDiscovered==false){
                    blockMinedColumn = o.dBColumn;
                    blockDiscovered = true;
                }
            }
            blockDiscovered = true;
        }
        return blockMinedColumn;
    }

    public int getBlockValue() {
        return blockValue;
    }

    public String getBlockMined() {
        return blockMined;
    }
}