package com.seitzsoftware.game.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.seitzsoftware.Player.Ore;
import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.database.DBColumn;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.UIButton;
import com.seitzsoftware.Player.RandomOre;

/**
 * Created by vette on 6/11/2017.
 */

public class MineState extends State {

    private RandomOre randomOre;
    private UIButton backButton;



    @Override
    public void init() {
        randomOre = new RandomOre();
        backButton = new UIButton(10,10,75,50,Assets.back_button,Assets.back_button);

    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        g.drawImage(Assets.mining_background, 0, 0,GameMainActivity.GAME_WIDTH,GameMainActivity.GAME_HEIGHT);
        backButton.render(g);
        renderResult(g);
        randomOre.uiButton.render(g);
        //renderBlock(g);

    }

    private void renderBlock(Painter g) {
        g.drawImage(GameMainActivity.N.coal_Ore.imageBitmap, (int) randomOre.getX(), (int) randomOre.getY(), 30, 30);
    }

    private void renderResult(Painter g){
        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT_BOLD, 20);
        g.drawString(randomOre.getBlockMined(), 120, 20);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            backButton.onTouchDown(scaledX, scaledY);
            randomOre.uiButton.onTouchDown(scaledX,scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (randomOre.uiButton.isPressed(scaledX,scaledY)){
                randomOre.uiButton.cancel();
                randomOre.onClick();
            }
            else if (backButton.isPressed(scaledX, scaledY)) {
                backButton.cancel();
                setCurrentState(new MenuState());
            }else{
                randomOre.uiButton.cancel();
                backButton.cancel();
            }
        }
        return true;


    }
    


    }
