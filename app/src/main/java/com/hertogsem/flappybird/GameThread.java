package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by janlindenberg on 13/01/2017.
 */

public class GameThread extends Thread{

    private GameSurfaceView surfaceView;
    private Boolean running;

    public GameThread(GameSurfaceView view) {
        this.surfaceView = view;
        this.running = false;
    }

    public void running(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        while(running) {

            // Update canvas
            Canvas canvas = surfaceView.getHolder().lockCanvas();
            if(canvas != null) {
                synchronized (surfaceView.getHolder()) {
                    surfaceView.updateGame(canvas);
                }
                surfaceView.getHolder().unlockCanvasAndPost(canvas);
            }

            // Sleep
            try {
                sleep(1000/60);
            } catch (InterruptedException ex) {
                Log.e("", ex.getLocalizedMessage());
            }
        }
    }

    public synchronized void begin() {
        running(true);
        start();
    }


    public final void end() throws InterruptedException {
        running(false);
        join();
    }


}
