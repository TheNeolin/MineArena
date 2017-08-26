package com.seitzsoftware.game.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.seitzsoftware.Player.Furnace;
import com.seitzsoftware.Player.FurnaceQueue;
import com.seitzsoftware.Player.Ore;
import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.UIButton;

public class FurnaceState extends State {
    private UIButton backButton, addToQueue, melt;
    private Furnace furnace;
    private FurnaceQueue furnaceQueue;


    @Override
    public void init() {
        backButton = new UIButton(10,10,75,50,Assets.back_button,Assets.back_button);
        furnace = new Furnace();
        furnaceQueue = new FurnaceQueue();
        addToQueue = new UIButton(250,300,350,350, Assets.addtofurnace_button,Assets.addtofurnace_button);
        melt = new UIButton(250,370,350,420,Assets.melt_button,Assets.melt_button);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        renderFurnace(g);
        addToQueue.render(g);
        melt.render(g);
        renderOres(g);
        renderFurnaceQueue(g);
        backButton.render(g);
        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT,20);
        g.drawString(Integer.toString(furnace.numberBarsCreated),GameMainActivity.GAME_WIDTH/3,GameMainActivity.GAME_HEIGHT/2 + 40);
    }

    private void renderOres(Painter g) {
        int x = GameMainActivity.GAME_WIDTH/2 + 50;
        int y = GameMainActivity.GAME_HEIGHT/2 + 50;
        for (Ore o : GameMainActivity.N.getOres()) {
            g.drawImage(o.imageBitmap, x, y, 20, 20);
            g.setColor(Color.WHITE);
            g.setFont(Typeface.DEFAULT,20);
            g.drawString(Integer.toString(o.characterAmount),x+30,y+20);
            y = y + 25;
            if (y == GameMainActivity.GAME_HEIGHT/2 + 175){
                x = GameMainActivity.GAME_WIDTH/2 + 200;
                y = GameMainActivity.GAME_HEIGHT/2 + 50;
            }
        }

    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            addToQueue.onTouchDown(scaledX, scaledY);
            melt.onTouchDown(scaledX, scaledY);
            backButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP && getOreClicked(e,scaledX,scaledY) != null) {
            furnaceQueue.addToFurnaceQueue(getOreClicked(e,scaledX,scaledY));
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (addToQueue.isPressed(scaledX, scaledY)) {
                addToQueue.cancel();
                addToFurnace(furnaceQueue.oreInQueue);

            } else if (melt.isPressed(scaledX, scaledY)) {
                melt.cancel();
                furnace.meltResult(furnace.oreInSlot2);
                furnaceQueue.numberInQueue = 0;
                furnaceQueue.oreInQueue = null;

            } else if(backButton.isPressed(scaledX, scaledY)) {
                backButton.cancel();
                setCurrentState(new MenuState());

            } else{
                addToQueue.cancel();
                melt.cancel();
                backButton.cancel();
            }
        }
        return true;
    }

    public void renderFurnace(Painter g){
        if (furnace.furnaceStatus == "off"){
            g.drawImage(Assets.furnace_off,0,0,GameMainActivity.GAME_WIDTH,GameMainActivity.GAME_HEIGHT/2);
        }else if(furnace.furnaceStatus == "on"){
            g.drawImage(Assets.furnace_on,0,0,GameMainActivity.GAME_WIDTH,GameMainActivity.GAME_HEIGHT/2);
        }
        if(furnace.oreInSlot1 != null){
            g.drawImage(furnace.oreInSlot1.getOreImg(),GameMainActivity.GAME_WIDTH/2 - 30,GameMainActivity.GAME_HEIGHT*1/3);
            g.setColor(Color.WHITE);
            g.setFont(Typeface.DEFAULT,20);
            g.drawString(Integer.toString(furnace.oreAmountInSlot1),GameMainActivity.GAME_WIDTH/2 - 30,GameMainActivity.GAME_HEIGHT*1/3 + 40);
        }
        if(furnace.oreInSlot2 != null){
            g.drawImage(furnace.oreInSlot2.getOreImg(),GameMainActivity.GAME_WIDTH/2 + 30,GameMainActivity.GAME_HEIGHT*1/3);
            g.setColor(Color.WHITE);
            g.setFont(Typeface.DEFAULT,20);
            g.drawString(Integer.toString(furnace.oreAmountInSlot2),GameMainActivity.GAME_WIDTH/2 + 30,GameMainActivity.GAME_HEIGHT*1/3 + 40);
        }
    }

    public void renderFurnaceQueue(Painter g){
        if(furnaceQueue.oreInQueue != null){
            g.drawImage(furnaceQueue.oreInQueue.getOreImg(),GameMainActivity.GAME_WIDTH/4,GameMainActivity.GAME_HEIGHT*2/3);
            g.setColor(Color.WHITE);
            g.setFont(Typeface.DEFAULT,20);
            g.drawString(Integer.toString(furnaceQueue.numberInQueue),GameMainActivity.GAME_WIDTH/4,GameMainActivity.GAME_HEIGHT*3/4);
        }
    }


    public Ore getOreClicked(MotionEvent e, int scaledX, int scaledY){
        Ore t = null;
        double x = GameMainActivity.GAME_WIDTH/2 + 50;
        double y = GameMainActivity.GAME_HEIGHT/2 + 50;
        for (Ore o : GameMainActivity.N.getOres()){
            if(scaledX >= x && scaledX <= x+20 && scaledY >= y && scaledY <= y+20){
                t = o;
                y = y +25;
                if (y == GameMainActivity.GAME_HEIGHT/2 + 175){
                    x = GameMainActivity.GAME_WIDTH/2 + 200;
                    y = GameMainActivity.GAME_HEIGHT/2 + 50;
                }
            }else{
                y = y +25;
                if (y == GameMainActivity.GAME_HEIGHT/2 + 175){
                    x = GameMainActivity.GAME_WIDTH/2 + 200;
                    y = GameMainActivity.GAME_HEIGHT/2 + 50;
                }
            }
        }
        return t;
    }

    public void addToFurnace(Ore o){
        if(o == GameMainActivity.N.coal_Ore && furnace.oreInSlot1 == null){
            furnace.oreInSlot1 = o;
            furnace.oreAmountInSlot1 = furnaceQueue.numberInQueue;
            furnaceQueue.oreInQueue = null;
            furnaceQueue.numberInQueue = 0;
        }else if (furnace.oreInSlot2 == null){
            furnace.oreInSlot2 = o;
            furnace.oreAmountInSlot2 = furnaceQueue.numberInQueue;
            furnaceQueue.oreInQueue = null;
            furnaceQueue.numberInQueue = 0;
        }

    }


}