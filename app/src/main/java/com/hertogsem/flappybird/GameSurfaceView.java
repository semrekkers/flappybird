package com.hertogsem.flappybird;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView {
    private static final String TAG = "GameSurfaceView";

    private GameThread thread;
    private GameLoop loop;

    public GameSurfaceView(Context context) {
        super(context);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        try {
            this.loop = new GameLoop(this);
            this.thread = new GameThread(loop);
        }
        catch (Exception ex) {
            Log.e(TAG, "Exception while initializing: "+ex.getMessage());
        }

        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                // Start GameThread if not running
                if (!thread.isRunning()) {
                    thread.startThread();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                try {
                    // Stop thread if running
                    if (thread.isRunning()) {
                        thread.stopThread();
                    }
                }
                catch (InterruptedException ex) {
                    Log.e(TAG, "stopThread failed: "+ex.getMessage());
                }
            }
        });
    }
}