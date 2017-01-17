package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Created by Sem Rekkers on 15-1-2017.
 */

public class Ground extends ResponsiveImage {
    public static final int HEIGHT = 128;
    public static final int WIDTH = 768;

    public Ground(Context context) {
        super(BitmapFactory.decodeResource(context.getResources(), R.drawable.ground_full));
    }

    @Override
    protected Rect updateDrawPoint(long time, int width, int height) {
        return new Rect(0, height - Ground.HEIGHT, width, height);
    }
}
