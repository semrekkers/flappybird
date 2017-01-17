package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView {
    private static final String TAG = "GameSurfaceView";

    private GameThread thread;
    private GameLoop loop;

    private Player player;
    private Point playerPoint;

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

        player = new Player(new Rect(100,100, 200,200), Color.RED);
        playerPoint = new Point(200,200);

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
                // Start GameThread
                thread.startThread();
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                try {
                    thread.stopThread();
                }
                catch (InterruptedException ex) {
                    Log.e(TAG, "stopThread failed: "+ex.getMessage());
                }
            }
        });
    }
    public void update() {
        player.update(playerPoint);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();
        try {
            Background background = new Background(this.getContext());
            background.draw(canvas, paint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.draw(canvas, paint);


    }
}
