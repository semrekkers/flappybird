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

        populatePipes();
        //pipes.add(new Pipe(context, 5*Constants.SCREEN_WIDTH / 4, 650, 400));
    }

    public void draw(Canvas canvas, Paint paint) {
        for (Pipe pipe: pipes) {
            pipe.draw(canvas, paint);
        }

    }

    public void populatePipes() {
        int currentX = 5 * Constants.SCREEN_WIDTH / 4;
        for (int i = 0; i<= 4; i++){
            int yStart = (int) (Math.random() * (Constants.SCREEN_HEIGHT - playerGap));
            pipes.add(new Pipe(context, currentX, yStart, playerGap));
            currentX += pipeGap + Constants.PIPE_WIDTH;
            i++;
        }
    }

    public void update() {
        float speed = Constants.SCREEN_WIDTH / 100.0f;
        for (Pipe pipe : pipes) {
            pipe.decrementX(speed);
        }
        if (pipes.get(0).getRectangle().right <= 0) {
            int yStart = (int) (Math.random() * (Constants.SCREEN_HEIGHT - playerGap));
            pipes.add(new Pipe(context, pipes.get(pipes.size() - 1).getRectangle().right + pipeGap, yStart, playerGap));
            System.out.println(pipes.remove(0));
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