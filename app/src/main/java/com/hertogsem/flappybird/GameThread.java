package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by janlindenberg on 13/01/2017.
 */

public class GameThread extends Thread{

    private GameSurfaceView surfaceView;
    private Boolean isRunning;

    public GameThread(GameSurfaceView view) {
        this.surfaceView = view;
        this.isRunning = false;
    }

    public void running(boolean running) {
        this.isRunning = running;
    }

    @Override
    public void run() {
        while(isRunning) {

            // Update canvas
            Canvas canvas = surfaceView.getHolder().lockCanvas();
            if( canvas != null ) {
                synchronized (surfaceView.getHolder()) {
                    surfaceView.updateGame(canvas);
                }
                surfaceView.getHolder().unlockCanvasAndPost(canvas);
            }

            // Sleep
            try { sleep(1000/60); } catch (InterruptedException ex) {
                Log.e("", ex.getLocalizedMessage());
            }
        }
    }

}
