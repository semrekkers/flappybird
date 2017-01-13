package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface GameObject {
    /**
     * Update updates the properties of the game object.
     * @param time Elapsed time in milliseconds.
     * @param width Width of the canvas.
     * @param height Height of the canvas.
     */
    void update(long time, int width, int height);

    /**
     * Draw draws the object to the canvas.
     * @param canvas The game canvas.
     * @param paint The canvas paint.
     */
    void draw(Canvas canvas, Paint paint);
}
