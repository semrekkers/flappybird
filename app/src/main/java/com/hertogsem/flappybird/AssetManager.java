package com.hertogsem.flappybird;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Sem Rekkers on 16-1-2017.
 */

public class AssetManager {
    private static AssetManager instance;

    private OnlyOnce initialized = new OnlyOnce();

    private Context context;

    private Bitmap background;
    private Bitmap ground;
    private Bitmap bird;
    private Bitmap pipe;
    private Bitmap rotatedPipe;

    protected AssetManager(Context context) {
        Resources res = context.getResources();
        this.context = context;

        background = BitmapFactory.decodeResource(res, R.drawable.background);
        ground = BitmapFactory.decodeResource(res, R.drawable.ground);
        bird = BitmapFactory.decodeResource(res, R.drawable.bird);
        pipe = BitmapFactory.decodeResource(res, R.drawable.pipe);
        rotatedPipe = Helper.rotateBitmap180(pipe);
    }

    public void init(Context context) {
        synchronized (instance) {

        }
    }
}
