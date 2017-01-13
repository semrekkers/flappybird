package com.hertogsem.flappybird;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView {

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

    }

    protected void updateGame() {
        
    }

}
