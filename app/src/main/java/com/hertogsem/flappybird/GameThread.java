package com.hertogsem.flappybird;

        import android.graphics.Canvas;
        import android.provider.Settings;
        import android.util.Log;

/**
 * Created by janlindenberg on 13/01/2017.
 */

public class GameThread extends Thread {
    public static final String TAG = "GameThread";

    public static final int LOG_SKIPPED_FRAMES = 3;

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
        boolean skipFrame = false;
        int skipCount = 0;

        while (isRunning()) {
            // Check for frame skipping
            if (!skipFrame) {
                // Run the GameLoop once
                time = System.currentTimeMillis();
                loop.run();
                duration = System.currentTimeMillis() - time;

                // Calculate sleep time
                sleepTime = (1000 / getFps()) - duration;
                if (sleepTime > 0) {
                    try {
                        // Try to sleep if there is sleeptime
                        sleep(sleepTime);
                    }
                    catch (InterruptedException ex) {
                        Log.e(TAG, "Before or while sleep: " + ex.getMessage());
                    }
                }
                else {
                    // There is no sleeptime, skip the next frame.
                    skipFrame = true;
                }
            }
            else {
                skipFrame = false;
                skipCount++;
                if (skipCount > LOG_SKIPPED_FRAMES) {
                    skipCount = 0;
                    Log.i(TAG, "Skipped " + LOG_SKIPPED_FRAMES + " frames!");
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
