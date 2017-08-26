package com.seitzsoftware.android.simpleandroidgdf;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;

import com.seitzsoftware.Player.Character;
import com.seitzsoftware.Player.Forge;
import com.seitzsoftware.database.MyDBHandler;

/**
 * Created by Kevin on 12/13/2015.
 */
public class GameMainActivity extends Activity {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;
    public static GameView sGame;
    public static AssetManager assets;
    public static MyDBHandler dbHandler;

    //test to see if we can update from a clone!!

    private static SharedPreferences prefs;
    private static String userName = "N";
    public static Character N;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        N = new Character();
        assets = getAssets();
        dbHandler = new MyDBHandler(this,null,null,1);




        sGame = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        setContentView(sGame);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public static void setUserName(String userName){
        GameMainActivity.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }
}
