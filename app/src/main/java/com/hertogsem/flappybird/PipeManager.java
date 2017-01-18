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

    private long startTime;
    private long initTime;

    private int score= 0;
    float speedMultiplier;

    private int pipeColor = Color.BLACK;
    private int backGroundColor = Color.WHITE;

    public PipeManager(int playerGap, int pipeGap) {
        this.playerGap = playerGap;
        this.pipeGap = pipeGap;

        startTime = System.currentTimeMillis();
        initTime = System.currentTimeMillis();

        pipes = new ArrayList<>();

        populatePipes();
    }

    /**
     * draws background color. calls draw method for all pipes. draws score.
     * @param canvas
     * @param paint
     */
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawColor(backGroundColor);

        for (Pipe pipe: pipes) {
            pipe.draw(canvas, paint);
        }

        paint.setColor(Color.BLUE);
        paint.setTextSize(100);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent() , paint);
        //canvas.drawText("" + speedMultiplier, 300, 50 + paint.descent() - paint.ascent() , paint);

    }

    /**
     * initial ammount of pipes at the start of the game
     */
    public void populatePipes() {
        int currentX = 5 * Constants.SCREEN_WIDTH / 4;
        for (int i = 0; i < 5; i++){
            //random height of the playerGap
            int yStart = (int) (Math.random() * (Constants.SCREEN_HEIGHT - playerGap));
            pipes.add(new Pipe(currentX, yStart, playerGap, pipeColor));
            currentX += pipeGap + Constants.PIPE_WIDTH;
            i++;
        }
    }

    public void update() {
        boolean doOnce = false;

        //calculates the movement speed of the pipes
        startTime = System.currentTimeMillis();
        float speed =  Constants.SCREEN_WIDTH / (300.0f);
        speedMultiplier = (float) (Math.sqrt(1 + (startTime - initTime) / (10000.0)));
        speed *= speedMultiplier;

        // decrements the x for the movement of the pipes
        for (Pipe pipe : pipes) {
            pipe.decrementX(speed);
        }

        //if rectangle leaves the screen it will be removed and a new one will be instanciated
        if (pipes.get(0).getRectangle().right <= 0) {
            int yStart = (int) (Math.random() * (Constants.SCREEN_HEIGHT - playerGap));
            pipes.add(new Pipe(pipes.get(pipes.size() - 1).getRectangle().right + pipeGap, yStart, playerGap, pipeColor));
            pipes.remove(0);
            score++;
            doOnce = true;
        }

        //changes the color of the screen and pipes every 5 pipes that leave the screen
        if(score != 0 && score %5 == 0 && doOnce) {
            int red= (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            pipeColor = Color.rgb(red, green, blue);
            for (Pipe pipe: pipes ) {
                pipe.setColor(pipeColor);
            }
            backGroundColor = Color.rgb(255 - red, 255 - green, 255 - blue);
        }

    }

    /**
     * calls the colission method for all the pipes
     * @param player
     * @return
     * true if player collided
     */
    public boolean playerColide(Player player) {
        for(Pipe pipe : pipes) {
            if(pipe.playerCollide(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * returns player score
     * @return
     */
    public int getScore() {
        return score;
    }
}