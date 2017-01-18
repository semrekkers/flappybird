package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by janlindenberg on 17/01/2017.
 */

/**
 * pipes are the obstaceles the player has to avoid
 */
public class Pipe implements GameObject {

    private Rect pipeRect;
    private Rect pipeRect2;
    private int color;

    /**
     * Constructor of the pipe
     * @param startX
     * starting x
     * @param startY
     * starting y, top of gap
     * @param playerGap
     * the height of the gap for the player to pass through
     * @param color
     * color of the pipes
     */
    public Pipe(int startX, int startY, int playerGap, int color) {
        this.color = color;

        this.pipeRect = new Rect(0 + startX, startY + playerGap, Constants.PIPE_WIDTH + startX, Constants.SCREEN_HEIGHT);
        this.pipeRect2 = new Rect(0 + startX, 0, Constants.PIPE_WIDTH + startX, startY);

    }

    @Override
    public void update(long time, int width, int height) {

    }

    /*
    draws the pipes in the chosen color
     */
    @Override
    public void draw(Canvas canvas, Paint paint) {

        paint.setColor(color);
        canvas.drawRect(pipeRect, paint);
        canvas.drawRect(pipeRect2, paint);

    }

    /**
     * Decrements the x of the pipes to move the pipes to the left of the screen
     * @param x
     * ammount the x decrements
     */
    public void decrementX(Float x) {
        pipeRect.left -= x;
        pipeRect.right -= x;
        pipeRect2.left -= x;
        pipeRect2.right -= x;
    }

    /**
     * Checks if the player collided with one of the pipes
     * @param player
     * @return
     * player collided?
     */
    public boolean playerCollide(Player player) {
        return Rect.intersects(pipeRect, player.getRectangle()) || Rect.intersects(pipeRect2, player.getRectangle());
    }

    /**
     * gets the lower rectangle
     * @return
     */
    public Rect getRectangle() {
        return pipeRect;
    }

    /**
     * sets the color of the pipes
     * @param color
     */
    public void setColor(int color) {
        this.color = color;
    }
}
