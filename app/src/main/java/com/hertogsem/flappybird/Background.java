package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceView;

import java.io.IOException;

public class Background implements GameObject {

    public static final String BACKGROUND_ASSET = "background.png";
    public static int GROUND_HEIGHT = 128;

    private Context context;
    private Bitmap backgroundImage;
    private Rect backgroundRect;

    public Background(Context context) throws IOException {
        this.context = context;
        this.backgroundImage = Helper.getBitmapFromAsset(context, BACKGROUND_ASSET);
        this.backgroundRect = new Rect(0, 0, backgroundImage.getWidth(), backgroundImage.getHeight());
    }

    @Override
    public void update(long time, int width, int height) {

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        // Draw the background
        int backgroundHeight = canvas.getHeight() - GROUND_HEIGHT;
        Rect destRect = new Rect(0, 0, canvas.getWidth(), backgroundHeight);
        canvas.drawBitmap(backgroundImage, backgroundRect, destRect, paint);
    }

}
