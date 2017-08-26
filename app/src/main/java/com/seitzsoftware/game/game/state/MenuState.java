package com.seitzsoftware.game.game.state;

import android.graphics.Color;
import android.view.MotionEvent;

import com.seitzsoftware.Player.Armor;
import com.seitzsoftware.Player.Forge;
import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.UIButton;

public class MenuState extends State {

    private UIButton mineButton, characterButton, furnaceButton, forgeButton, fightingButton;


    @Override
    public void init() {

        mineButton = new UIButton(100,150,150,200,Assets.axe,Assets.axe);
        characterButton = new UIButton(200,150,250,200,Assets.character,Assets.character);
        furnaceButton = new UIButton(300,150,350,200,Assets.furnace_button,Assets.furnace_button);
        forgeButton = new UIButton(450,150,500,200,Assets.anvil_button,Assets.anvil_button);
        fightingButton = new UIButton(550,150,600,200,Assets.fighting_button,Assets.fighting_button);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        g.drawImage(Assets.menu_background, 0, 0,GameMainActivity.GAME_WIDTH,GameMainActivity.GAME_HEIGHT);
        mineButton.render(g);
        characterButton.render(g);
        furnaceButton.render(g);
        forgeButton.render(g);
        fightingButton.render(g);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            mineButton.onTouchDown(scaledX, scaledY);
            characterButton.onTouchDown(scaledX, scaledY);
            furnaceButton.onTouchDown(scaledX, scaledY);
            forgeButton.onTouchDown(scaledX, scaledY);
            fightingButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (mineButton.isPressed(scaledX, scaledY)) {
                mineButton.cancel();
                setCurrentState(new MineState());

            } else if (characterButton.isPressed(scaledX, scaledY)) {
                characterButton.cancel();
                GameMainActivity.N.loadCharacter();
                setCurrentState(new CharacterState());

            } else if (furnaceButton.isPressed(scaledX, scaledY)) {
                furnaceButton.cancel();
                GameMainActivity.N.loadCharacter();
                setCurrentState(new FurnaceState());

            }else if (forgeButton.isPressed(scaledX, scaledY)) {
                forgeButton.cancel();
                GameMainActivity.N.loadCharacter();
                setCurrentState(new ForgeState());

            }else if (fightingButton.isPressed(scaledX, scaledY)) {
                fightingButton.cancel();


            }
            else {
                mineButton.cancel();
                characterButton.cancel();
                furnaceButton.cancel();
                forgeButton.cancel();
                fightingButton.cancel();
            }
        }
        return true;
    }


}