package com.seitzsoftware.android.simpleandroidgdf;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.AudioManager;
import android.media.SoundPool;

import com.seitzsoftware.Player.Bar;
import com.seitzsoftware.Player.Ore;

import java.io.IOException;
import java.io.InputStream;

public class Assets {
    private static SoundPool soundPool;
    public static Bitmap welcome,mine_arena_logo,login_button,addtofurnace_button,forge_button,melt_button,createaccount_button,back_button,axe,mining_background,menu_background,character,furnace_button,anvil_button,fighting_button,furnace_on,furnace_off,dwarf_blue;
    public static int backgoundMusic;

    public static void load() {
        welcome = loadBitmap("welcome.png", false);
        back_button = loadBitmap("back_button.png",false);
        login_button = loadBitmap("login_button.png",false);
        createaccount_button = loadBitmap("createaccount_button.png",false);
        mine_arena_logo = loadBitmap("mine_arena_logo.png", false);
        axe = loadBitmap("axe.png", false);
        character = loadBitmap("bag.png", false);
        furnace_button = loadBitmap("furnace_button.png",false);
        anvil_button = loadBitmap("anvil_button.png",false);
        addtofurnace_button = loadBitmap("addtofurnace_button.png",false);
        forge_button = loadBitmap("forge_button.png",false);
        melt_button = loadBitmap("melt_button.png",false);
        fighting_button = loadBitmap("fighting_button.png",false);
        mining_background = loadBitmap("mining_background.png",false);
        menu_background = loadBitmap("menu_background.png",false);
        furnace_on = loadBitmap("furnace_on.png",false);
        furnace_off = loadBitmap("furnace_off.png",false);

        for (Ore o : GameMainActivity.N.getOres()) {
            o.imageBitmap = loadBitmap(o.imageString,false);
        }

        for (Bar b : GameMainActivity.N.getBars()) {
            b.imageBitmap = loadBitmap(b.imageString,true);
        }

        dwarf_blue = loadBitmap("dwarf_2_blue.png",false);

        backgoundMusic = loadSound("mine_arena_music.mp3");
        
    }

    private static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;
        try {
            inputStream = GameMainActivity.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
        if (transparency) {
            options.inPreferredConfig = Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null,
                options);
        return bitmap;
    }

    private static int loadSound(String filename) {
        int soundID = 0;
        if (soundPool == null) {
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        try {
            soundID = soundPool.load(GameMainActivity.assets.openFd(filename),
                    1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soundID;
    }

    public static void playSound(int soundID) {
        soundPool.play(soundID, 1, 1, 1, 0, 1);
    }
}
