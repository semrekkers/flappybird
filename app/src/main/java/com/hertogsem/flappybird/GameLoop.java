package com.hertogsem.flappybird;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Sem Rekkers on 15-1-2017.
 */

public class GameLoop {
    private static final String TAG = "GameLoop";

    private Object loopLock = new Object();
    private GameSurfaceView gameSurfaceView;

    // Thread safe
    private Paint paint;
    private Background background;

    public GameLoop(GameSurfaceView gameSurfaceView) throws Exception {
        this.gameSurfaceView = gameSurfaceView;

        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.background = new Background(gameSurfaceView.getContext());
    }

    public void run() {
        synchronized (loopLock) {
            Canvas canvas = gameSurfaceView.getHolder().lockCanvas();
            if (canvas != null) {
                synchronized (gameSurfaceView.getHolder()) {
                    loopOnce(canvas);
                }
                gameSurfaceView.getHolder().unlockCanvasAndPost(canvas);
            }
        }
    }

    private void loopOnce(Canvas canvas) {
        long time = System.currentTimeMillis();
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Only for testing
        background.update(time, width, height);
        background.draw(canvas, paint);
    }

}
