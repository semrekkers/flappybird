package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Canvas;
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

    public PipeManager(Context context, int playerGap, int pipeGap) {
        this.context = context;
        this.playerGap = playerGap;
        this.pipeGap = pipeGap;

        startTime = System.currentTimeMillis();
        initTime = System.currentTimeMillis();

        pipes = new ArrayList<>();

        //populatePipes();
        pipes.add(new Pipe(context, 5*Constants.SCREEN_WIDTH / 4, 650, 400));
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Pipe pipe: pipes) {
            pipe.draw(canvas, paint);
        }

    }

    public void populatePipes() {
        int currentX = 5 * Constants.SCREEN_WIDTH / 4;
        while (currentX > 0) {
            int yStart = (int) (Math.random() * (Constants.SCREEN_HEIGHT - playerGap));
            pipes.add(new Pipe(context, currentX, yStart, playerGap));
            currentX += pipeGap;
        }
    }

    public void update() {
        float speed = Constants.SCREEN_WIDTH / 100.0f;
        System.out.println("speed" + speed);
        for (Pipe pipe : pipes) {
            pipe.decrementX(speed);
        }
//        if (pipes.get(pipes.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
//            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
//            pipes.add(0, new Pipe(context, xStart, pipes.get(0).getRectangle().top + playerGap, playerGap));
//            pipes.remove(pipes.size() - 1);
//        }
    }
}