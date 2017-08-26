package com.seitzsoftware.game.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.seitzsoftware.Player.Armor;
import com.seitzsoftware.Player.Bar;
import com.seitzsoftware.Player.Character;
import com.seitzsoftware.Player.CharacterStat;
import com.seitzsoftware.Player.Ore;
import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.database.DBColumn;
import com.seitzsoftware.database.MyDBHandler;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.UIButton;


/**
 * Created by vette on 6/14/2017.
 */

public class CharacterState extends State {

    private UIButton backButton;


    @Override
    public void init() {
        backButton = new UIButton(10,10,75,50,Assets.back_button,Assets.back_button);


        /*
        mineButton = new UIButton(300,200,400,300, Assets.axe,Assets.axe);
        characterButton = new UIButton(300,250,400,350,Assets.character,Assets.character);
        */
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);

        g.drawImage(Assets.dwarf_blue, 0, 0,GameMainActivity.GAME_WIDTH / 3,GameMainActivity.GAME_HEIGHT);
        backButton.render(g);
        renderInventory(g);
        renderCharacterStats(g);
        renderCharacterItems(g);
    }

    private void renderInventory(Painter g) {
        int x = GameMainActivity.GAME_WIDTH - 200;
        int y = 100;
        for (Ore o : GameMainActivity.N.getOres()) {
            g.drawImage(o.imageBitmap, x, y, 20, 20);
            g.setColor(Color.WHITE);
            g.setFont(Typeface.DEFAULT,20);
            g.drawString(Integer.toString(o.characterAmount),x+30,y+20);
            y = y + 25;
        }
        x = GameMainActivity.GAME_WIDTH - 125;
        y = 125;
        for (Bar b : GameMainActivity.N.getBars()) {
            g.drawImage(b.imageBitmap, x, y, 40, 20);
            g.setColor(Color.WHITE);
            g.setFont(Typeface.DEFAULT,20);
            g.drawString(Integer.toString(b.characterAmount),x+50,y+20);
            y = y + 25;
        }

    }

    private void renderCharacterStats(Painter g){
        int x = GameMainActivity.GAME_WIDTH/2 - 100;
        int y = 100;
        for (CharacterStat c : GameMainActivity.N.getStats()){
            g.setFont(Typeface.DEFAULT,15);
            g.drawString(c.statName,x,y);
            g.drawString(Integer.toString(c.statValue),x+75,y);
            y = y + 25;
        }
    }

    private void renderCharacterItems(Painter g){
        int x = GameMainActivity.GAME_WIDTH/2 - 100;
        int y = 200;
        for (Armor a : GameMainActivity.N.getArmor()){
            g.setFont(Typeface.DEFAULT,15);
            g.drawString(a.material,x,y);
            g.drawString(a.dBColumn.getObjDBColumnName(),x+100,y);
            y = y + 25;
        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            backButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (backButton.isPressed(scaledX, scaledY)) {
                backButton.cancel();
                setCurrentState(new MenuState());
            }
        }
        return true;
    }


}