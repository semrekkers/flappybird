package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Sem Rekkers on 17-1-2017.
 */

public class ResponsiveImage implements GameObject {

    protected Bitmap image;
    protected Rect size;

    private Rect drawPoint;

    public ResponsiveImage(Bitmap image) {
        this.image = image;
        this.size = new Rect(0, 0, image.getWidth(), image.getHeight());
        this.drawPoint = new Rect();
    }

    @Override
    public void update(long time, int width, int height) {
        drawPoint = updateDrawPoint(time, width, height);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(image, size, drawPoint, paint);
    }

    protected Rect updateDrawPoint(long time, int width, int height) {
        return new Rect(0, 0, size.width(), size.height());
    }
}
