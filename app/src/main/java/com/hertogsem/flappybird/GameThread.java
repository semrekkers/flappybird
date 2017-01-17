package com.hertogsem.flappybird;

import android.util.Log;

/**
 * Created by janlindenberg on 13/01/2017.
 */

public class GameThread extends Thread{
    public static final String TAG = "GameThread";

    private GameLoop loop;

    private Object lock = new Object();
    private int fps;
    private Boolean running;

    public GameThread(GameLoop loop) {
        this.loop = loop;
        this.fps = 60;
        this.running = false;
    }

    @Override
    public void run() {
        long time, duration, sleepTime;

        while (isRunning()) {
            time = System.currentTimeMillis();
            loop.run();
            duration = System.currentTimeMillis() - time;

            sleepTime = (1000 / getFps()) - duration;
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                }
                catch (InterruptedException ex) {
                    Log.e(TAG, "Before or while sleep: " + ex.getMessage());
                }
            }
        }
    }

    public void startThread() {
        setRunning(true);
        start();
    }

    public void stopThread() throws InterruptedException {
        setRunning(false);
        join();
    }

    public int getFps() {
        synchronized (lock) {
            return fps;
        }
    }

    public void setFps(int fps) {
        synchronized (lock) {
            this.fps = fps;
        }
    }

    public boolean isRunning() {
        synchronized (lock) {
            return running;
        }
    }

    private void setRunning(boolean running) {
        synchronized (lock) {
            this.running = running;
        }
    }
}
