package com.seitzsoftware.game.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.provider.Settings;
import android.view.MotionEvent;
import android.widget.EditText;

import com.seitzsoftware.Player.Character;
import com.seitzsoftware.android.simpleandroidgdf.Assets;
import com.seitzsoftware.android.simpleandroidgdf.GameMainActivity;
import com.seitzsoftware.database.MyDBHandler;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.UIButton;

/**
 * Created by vette on 6/13/2017.
 */

public class LogInState extends State{

    EditText accountName;
    EditText password;
    private UIButton logInButton, newAccountButton;

    @Override
    public void init() {
        Assets.playSound(Assets.backgoundMusic);
        logInButton = new UIButton(200,250,300,300,Assets.login_button,Assets.login_button);
        newAccountButton = new UIButton(400,250,500,300,Assets.createaccount_button,Assets.createaccount_button);

    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        g.setColor(Color.WHITE);
        g.drawImage(Assets.mine_arena_logo, 0, 0,GameMainActivity.GAME_WIDTH,GameMainActivity.GAME_HEIGHT / 2);
        g.setFont(Typeface.DEFAULT_BOLD, 50);
        g.drawString("Mine Arena", 120, 175);
        logInButton.render(g);
        newAccountButton.render(g);
    }

    private void renderLogInText(Painter g){
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            logInButton.onTouchDown(scaledX, scaledY);
            newAccountButton.onTouchDown(scaledX, scaledY);
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            if (logInButton.isPressed(scaledX, scaledY)) {
                tryLogIn();
                logInButton.cancel();
            } else if (newAccountButton.isPressed(scaledX, scaledY)) {
                tryNewAccount();
                newAccountButton.cancel();
            } else {
                logInButton.cancel();
                newAccountButton.cancel();
            }
        }
        return true;
    }

    private void tryLogIn(){
        try{
            if (GameMainActivity.dbHandler.DBHasObject(MyDBHandler.TABLE_CHARACTER, MyDBHandler.COLUMN_ACCOUNTNAME, "N") && GameMainActivity.dbHandler.DBHasObject(MyDBHandler.TABLE_CHARACTER, MyDBHandler.COLUMN_PASSWORD, "1")) {
                System.out.println("Log in Success!");
                GameMainActivity.N.loadOreAmounts();
                setCurrentState(new MenuState());
            } else {
                System.out.println("Log in Failure!");
            }
        }catch (Exception ex){
        System.err.println("Caught Exception: " + ex.getMessage());
        }
    }

    private void tryNewAccount(){
        boolean accountExists = GameMainActivity.dbHandler.DBHasObject(MyDBHandler.TABLE_CHARACTER, MyDBHandler.COLUMN_ACCOUNTNAME, "N");
        if (accountExists) {
            System.out.println("User already exists!");
        } else {
            try {
                GameMainActivity.dbHandler.newCharacter("N","1");
                System.out.println("Create Account Success!");
            } catch (Exception ee) {
                System.err.println("Caught Exception: " + ee.getMessage());
            }
        }
    }
}
