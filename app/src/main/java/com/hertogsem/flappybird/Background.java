package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class Background implements GameObject {

    private Context context;
    private Bitmap backgroundImage;

    public Background(Context context) {
        this.context = context;
        //this.backgroundImage = Helper.getBitmapFromAsset(context, );
    }

    @Override
    public void update(long time, int width, int height) {

    }

    @Override
    public void draw(Canvas canvas) {

    }

}
