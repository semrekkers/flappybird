package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by janlindenberg on 17/01/2017.
 */

public class Pipe implements GameObject {

    private Context context;
    private Bitmap pipeImage;
    private Rect pipeRect;
    private Rect pipeRect2;

    private  int startX, startY, playerGap;

    public Pipe(Context context, int startX, int startY, int playerGap) {

        this.startX = startX;
        this.startY = startY;
        this.playerGap = playerGap;

        //this.pipeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.pipe);
        //this.pipeRect = new Rect(0 + startX, startY + playerGap, pipeImage.getWidth() + startX, pipeImage.getHeight() + startY);
        ///this.pipeRect2 = new Rect(0 + startX, startY - pipeImage.getHeight(), pipeImage.getWidth() + startX, startY);

        this.pipeRect = new Rect(0 + startX, startY + playerGap, Constants.PIPE_WIDTH + startX, Constants.PIPE_HEIGHT + startY);
        this.pipeRect2 = new Rect(0 + startX, startY - Constants.PIPE_HEIGHT, Constants.PIPE_WIDTH + startX, startY);

    }

    @Override
    public void update(long time, int width, int height) {

    }

    public void update() {

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {

        //canvas.drawBitmap(pipeImage, null, pipeRect, paint);
        //canvas.drawBitmap(Helper.rotateBitmap180(pipeImage), null, pipeRect2, paint);

        paint.setColor(Color.BLACK);
        canvas.drawRect(pipeRect, paint);
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

    public Rect getRectangle() {
        return pipeRect;
    }
}
