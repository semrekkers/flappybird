package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by janlindenberg on 17/01/2017.
 */

public class Pipe implements GameObject {

    private Rect pipeRect;
    private Rect pipeRect2;
    private int color;

    private  int startX, startY, playerGap;

    public Pipe(int startX, int startY, int playerGap, int color) {

        this.startX = startX;
        this.startY = startY;
        this.playerGap = playerGap;
        this.color = color;

        this.pipeRect = new Rect(0 + startX, startY + playerGap, Constants.PIPE_WIDTH + startX, Constants.SCREEN_HEIGHT);
        this.pipeRect2 = new Rect(0 + startX, 0, Constants.PIPE_WIDTH + startX, startY);

    }

    @Override
    public void update(long time, int width, int height) {

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(color);
        canvas.drawRect(pipeRect, paint);
        paint.setColor(color);
        canvas.drawRect(pipeRect2, paint);

    }

    public void incrementX(Float x) {
        pipeRect.left += x;
        pipeRect.right += x;
        pipeRect2.left += x;
        pipeRect2.right += x;
    }

    public void decrementX(Float x) {
        pipeRect.left -= x;
        pipeRect.right -= x;
        pipeRect2.left -= x;
        pipeRect2.right -= x;
    }

    public boolean playerCollide(Player player) {
        return Rect.intersects(pipeRect, player.getRectangle()) || Rect.intersects(pipeRect2, player.getRectangle());
    }

    public Rect getRectangle() {
        return pipeRect;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
