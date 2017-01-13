package com.hertogsem.flappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView {
    private static final String TAG = "GameSurfaceView";

    private GameThread thread;
    private SurfaceHolder holder;

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
        this.thread = new GameThread(this);
        this.holder = getHolder();

        this.paint = new Paint();
        try {
            this.background = new Background(getContext());
        }
        catch (Exception ex) {
            Log.e(TAG, "Exception on constructing Background: "+ex.getMessage());
        }
    }

    // NOT THREAD SAFE
    private Background background;
    private Paint paint;
    // END OF NOT THREAD SAFE

    protected void updateGame(Canvas canvas) {
        long time = System.currentTimeMillis();
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Only for testing
        background.update(time, width, height);
        background.draw(canvas, paint);
    }

}
