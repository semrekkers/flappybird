package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.ArrayList;

public class Background implements GameObject {

    private Context context;
    private Bitmap backgroundImage;
    private Rect backgroundRect;

    private int groundPartsCount;
    private ArrayList<Ground> groundParts;

    private int backgroundHeight = 0;

    public Background(Context context) throws IOException {
        this.context = context;
        this.backgroundImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        this.backgroundRect = new Rect(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());

    }

    @Override
    public void update(long time, int width, int height) {
        this.backgroundHeight = height - Ground.HEIGHT;

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        // Draw the background
        int backgroundHeight = canvas.getHeight() - Ground.HEIGHT;
        Rect destRect = new Rect(0, 0, canvas.getWidth(), backgroundHeight);
        canvas.drawBitmap(backgroundImage, backgroundRect, destRect, paint);
    }

    public static int getGroundParts(int width) {
        return (int)Math.ceil(width / Ground.WIDTH);
    }
}
