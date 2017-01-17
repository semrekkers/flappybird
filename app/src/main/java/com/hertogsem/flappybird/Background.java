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
    private Bitmap groundImage;
    private Rect backgroundRect;

    private int groundPartsCount;
    private int groundPartsCoverage;
    private ArrayList<Ground> groundParts;

    private int backgroundHeight = 0;

    public Background(Context context) throws IOException {
        this.context = context;
        this.backgroundImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        this.groundImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.ground);
        this.backgroundRect = new Rect(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
        this.groundParts = new ArrayList<>();
    }

    @Override
    public void update(long time, int width, int height) {
        this.backgroundHeight = height - Ground.HEIGHT;


    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        // Draw the background
        Rect destRect = new Rect(0, 0, canvas.getWidth(), backgroundHeight);
        canvas.drawBitmap(backgroundImage, backgroundRect, destRect, paint);
    }

    private void updateGroundPartArray(int width) {
        groundParts.clear();
        groundPartsCount = getGroundParts(width);

        int x = -Ground.WIDTH;
        int y = backgroundRect.height();
        for (int i = 0; i < groundPartsCount; i++) {

        }
    }

    public static int getGroundParts(int width) {
        return ((int)Math.ceil(width / Ground.WIDTH)) + 2;
    }
}
