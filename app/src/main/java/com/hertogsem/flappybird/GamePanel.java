package com.hertogsem.flappybird;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by janlindenberg on 17/01/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;

    private Player player;
    private Point playerPoint;
    private PipeManager pipeManager;
    private PlayerAnimator playerAnimator;

    private Rect r = new Rect();

    private boolean attractionMode = true;
    private boolean movingPlayer = false;
    private boolean playerStart = false;
    private boolean gameOver = false;
    private long gameOverTime, currentTime;

    Paint paint = new Paint();

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        player = new Player(new Rect(100,100,200,200), Color.rgb(255, 0, 0));
        playerPoint = new Point(Constants.SCREEN_WIDTH/2 , 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        pipeManager = new PipeManager(400, 500);
        playerAnimator = new PlayerAnimator(playerPoint.y);

        setFocusable(true);
    }

    /**
     * Resets game to startingpoint
     */
    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH/2 , 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        pipeManager = new PipeManager(400, 500);
        movingPlayer = false;
        playerStart = false;
        attractionMode = true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY())) {
                    playerStart = true;
                    attractionMode = false;
                    movingPlayer = true;
                }
                if(gameOver && System.currentTimeMillis() - gameOverTime > 2000) {
                    showScoreboard();
                    reset();
                    gameOver = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!gameOver && movingPlayer) {
                    playerPoint.set((int)event.getX(), (int)event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }

        return true;
    }

    /**
     * Updates the game
     */
    public void update() {
        currentTime = System.currentTimeMillis();

        if (attractionMode) {
            //int y = playerAnimator.getNextYPosition(currentTime);
            //playerPoint.set(playerPoint.x, y);
            //player.update(playerPoint);
        }

        if(!gameOver && playerStart) {
            player.update(playerPoint);
            pipeManager.update();

            if(pipeManager.playerColide(player)) {
                playerStart = false;
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * draws all elements on screen
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //canvas.drawColor(Color.WHITE);

        pipeManager.draw(canvas, paint);
        player.draw(canvas, paint);

        if(!playerStart && !gameOver) {
            paint.setTextSize(100);
            paint.setFakeBoldText(true);
            paint.setColor(Color.RED);
            drawCenterText(canvas, paint, "press to move");
        }

        if(gameOver) {
            paint.setTextSize(100);
            paint.setFakeBoldText(true);
            paint.setColor(Color.RED);
            drawCenterText(canvas, paint, "Game Over");
        }
    }

    /**
     * Draws text in middle of the screen
     * @param canvas
     * @param paint
     * @param text
     * text to be drawn
     */
    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }

    /**
     * shows scoreboard
     */
    private void showScoreboard() {
        Intent intent = new Intent(this.getContext(), PlayerNameActivity.class);
        intent.putExtra(ScoreboardActivity.EXTRA_SCORE, pipeManager.getScore());
        this.getContext().startActivity(intent);
    }
}
