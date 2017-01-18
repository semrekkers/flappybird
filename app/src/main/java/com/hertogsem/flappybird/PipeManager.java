package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by janlindenberg on 17/01/2017.
 */

public class PipeManager {

    private ArrayList<Pipe> pipes;
    private int playerGap;
    private int pipeGap;
    private Context context;

    private long startTime;
    private long initTime;

    private int score= 0;
    float speedMultiplier;

    private int pipeColor = Color.BLACK;
    private int backGroundColor = Color.WHITE;

    public PipeManager(Context context, int playerGap, int pipeGap) {
        this.context = context;
        this.playerGap = playerGap;
        this.pipeGap = pipeGap;

        startTime = System.currentTimeMillis();
        initTime = System.currentTimeMillis();

        pipes = new ArrayList<>();

        populatePipes();
        //pipes.add(new Pipe(context, 5*Constants.SCREEN_WIDTH / 4, 650, 400));
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(backGroundColor);

        for (Pipe pipe: pipes) {
            pipe.draw(canvas, paint);
        }

        paint.setColor(Color.BLUE);
        paint.setTextSize(100);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent() , paint);
        canvas.drawText("" + speedMultiplier, 300, 50 + paint.descent() - paint.ascent() , paint);

    }

    public void populatePipes() {
        int currentX = 5 * Constants.SCREEN_WIDTH / 4;
        for (int i = 0; i <= 4; i++){
            int yStart = (int) (Math.random() * (Constants.SCREEN_HEIGHT - playerGap));
            pipes.add(new Pipe(currentX, yStart, playerGap, pipeColor));
            currentX += pipeGap + Constants.PIPE_WIDTH;
            i++;
        }
    }

    public void update() {
        boolean doOnce = false;
        int currentScore = score;
        startTime = System.currentTimeMillis();
        float speed =  Constants.SCREEN_WIDTH / (300.0f);
        speedMultiplier = (float) (Math.sqrt(1 + (startTime - initTime) / (10000.0)));
        speed *= speedMultiplier;
        for (Pipe pipe : pipes) {
            pipe.decrementX(speed);
        }
        if (pipes.get(0).getRectangle().right <= 0) {
            int yStart = (int) (Math.random() * (Constants.SCREEN_HEIGHT - playerGap));
            pipes.add(new Pipe(pipes.get(pipes.size() - 1).getRectangle().right + pipeGap, yStart, playerGap, pipeColor));
            pipes.remove(0);
            score++;
            doOnce = true;
        }
        if(score != 0 && score %5 == 0 && doOnce) {
            int red= (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            pipeColor = Color.rgb(red, green, blue);
            for (Pipe pipe: pipes ) {
                pipe.setColor(pipeColor);
            }
            backGroundColor = Color.rgb(255 - red, 255 - green, 255 - blue);
            doOnce = false;
        }

    }

    public boolean playerColide(Player player) {
        for(Pipe pipe : pipes) {
            if(pipe.playerCollide(player)) {
                return true;
            }
        }
        return false;
    }
}