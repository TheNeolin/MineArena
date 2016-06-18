package com.seitzsoftware.android.simpleandroidgdf;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.WindowManager;

/**
 * Created by Kevin on 12/13/2015.
 */
public class GameMainActivity extends Activity {
    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 450;
    public static GameView sGame;
    public static AssetManager assets;

    //test to see if we can update from a clone!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assets = getAssets();
        sGame = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
        setContentView(sGame);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
