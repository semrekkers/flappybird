package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.hertogsem.flappybird.GamePanel;

/**
 * Created by janlindenberg on 17/01/2017.
 */

public class MainThread extends Thread {

    public static final int MAX_FPS = 60;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private static Canvas canvas;
    private Object lock = new Object();

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }

    /**
     * Sets running
     * @param running
     */
    public void setRunning(boolean running) {
        synchronized (lock) {
            this.running = true;
        }
    }

    /**
     * returns running boolean
     * @return
     */
    public boolean isRunning() {
        synchronized (lock) {
            return this.running;
        }
    }

    @Override
    public void run() {

        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while(isRunning()) {
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.surfaceHolder.lockCanvas();
                if (canvas != null) {
                    synchronized (surfaceHolder) {
                        this.gamePanel.update();
                        this.gamePanel.draw(canvas);
                    }
                }
                if (canvas == null) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //gets time elapsed and calculates wait time
            timeMillis = (System.nanoTime() - startTime)/1000000;
            waitTime = targetTime - timeMillis;
            try {
                //if waittime over 0 sleep wait time to give even cycles
                if(waitTime > 0) {
                    sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // calculate total time
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) { // calculate the average fps
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                Log.v("MainThread", "Average FPS: " + averageFPS);
            }
        }

    }
}
