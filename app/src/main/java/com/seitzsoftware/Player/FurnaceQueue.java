package com.seitzsoftware.Player;

import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;

/**
 * Created by vette on 6/18/2017.
 */

public class FurnaceQueue {

    public int numberInQueue;
    public Ore oreInQueue;

    public FurnaceQueue(){
        numberInQueue = 0;

    }

    public void addToFurnaceQueue(Ore o){
        if(o.characterAmount != 0 && (oreInQueue==null || o==oreInQueue)) {
            oreInQueue = o;
            o.amountInFurnaceQueue++;
            o.characterAmount--;
            numberInQueue++;
        }
    }

}
