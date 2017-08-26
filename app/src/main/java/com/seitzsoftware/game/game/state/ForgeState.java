package com.seitzsoftware.game.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.seitzsoftware.Player.Armor;
import com.seitzsoftware.Player.Bar;
import com.seitzsoftware.Player.Forge;
import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.UIButton;

/**
 * Created by vette on 6/11/2017.
 */

public class ForgeState extends State{

    private UIButton backButton, forgeButton;
    private Forge forge;


    @Override
    public void init() {
        forge = new Forge();

        backButton = new UIButton(10,10,75,50, Assets.back_button,Assets.back_button);
        forgeButton = new UIButton(450,300,550,350, Assets.forge_button,Assets.forge_button);

    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        backButton.render(g);
        forgeButton.render(g);
        renderBars(g);
        renderBarSlots(g);
        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT,20);
        g.drawString(Integer.toString(0),GameMainActivity.GAME_WIDTH*2/3,GameMainActivity.GAME_HEIGHT/2 + 40);
    }



    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            forgeButton.onTouchDown(scaledX, scaledY);
            backButton.onTouchDown(scaledX, scaledY);
            for (Bar o : GameMainActivity.N.getBars()) {
                o.uiButton.onTouchDown(scaledX, scaledY);
            }
        }
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            for (Bar o : GameMainActivity.N.getBars()) {
                o.uiButton.onDrag(scaledX, scaledY, e);
            }
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            for (Bar o : GameMainActivity.N.getBars()) {
                for (Bar u : forge.getBarSlots()){
                    if(u.uiButton.buttonRect.contains(o.uiButton.buttonRect) && o.characterAmount>0 && u.amountInForge==0){
                        System.out.println("IT WORKED");
                        u.uiButton.buttonImage = o.uiButton.buttonImage;
                        u.amountInForge ++;
                        o.amountInForge ++;
                        o.characterAmount --;
                    }
                }
                o.uiButton.resetPosition();
            }
        }

        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (backButton.isPressed(scaledX, scaledY)) {
                backButton.cancel();
                setCurrentState(new MenuState());


            } else if (forgeButton.isPressed(scaledX, scaledY)) {
                forgeButton.cancel();
                forge.forgeResult();

            } else{
                backButton.cancel();
                forgeButton.cancel();
            }
        }
        return true;
    }


    private void renderBars(Painter g) {
        int left = 100;
        int top = 100;
        for (Bar b : GameMainActivity.N.getBars()){
            b.uiButton.render(g);
            top += 30;
            g.setColor(Color.WHITE);
            g.setFont(Typeface.DEFAULT,20);
            g.drawString(Integer.toString(b.characterAmount),left+50,top-10);

        }
    }

    private void renderBarSlots(Painter g){
        for (Bar u : forge.getBarSlots()){
            u.uiButton.render(g);
        }
    }

}