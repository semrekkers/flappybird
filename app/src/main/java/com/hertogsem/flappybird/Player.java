package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by janlindenberg on 17/01/2017.
 */

/**
 * the rectangle resembling the player
 */
public class Player implements GameObject {

    private Rect rectangle;
    private int color;

    public Player(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void update(long time, int width, int height) {

    }

    /**
     * Updates the point the player will be drawn at.
     * @param point
     * Point is the middle of the player.
     */
    public void update(Point point) {
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height() / 2, point.x + rectangle.width()/2, point.y + rectangle.height() / 2);
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    public Rect getRectangle() {
        return rectangle;
    }
}
