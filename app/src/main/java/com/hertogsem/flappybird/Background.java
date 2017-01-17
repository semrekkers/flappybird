package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Background extends ResponsiveImage {
    public static final int HEIGHT = 896;
    public static final int WIDTH = 768;

    public Background(Context context) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.background));
    }

    @Override
    protected Rect updateDrawPoint(long time, int width, int height) {
        return new Rect(0, 0, width, height - Ground.HEIGHT);
    }
}
