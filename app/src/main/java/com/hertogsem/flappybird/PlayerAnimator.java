package com.hertogsem.flappybird;

/**
 * Created by Sem Rekkers on 18-1-2017.
 */

public class PlayerAnimator {

    public static final float POINT_DELTA = 20f;
    public static final float TRANSITION_DURATION = 1000;
    public static final float TIME_CORRECTION = 10000000000f;

    private float upperY, lowerY;
    private float nextSwitch;
    private boolean goLower;

    public PlayerAnimator(int playerX) {
        this.upperY = (float)playerX;
        this.lowerY = upperY - POINT_DELTA;
        this.nextSwitch = 0f;
        this.goLower = true;
    }

    /**
     * Calculate next Y position for the player.
     * @param time Current time
     * @return Y position
     */
    public int getNextYPosition(long time) {
        float timeF = (float)time / TIME_CORRECTION;

        if (timeF >= nextSwitch) {
            // Switch animation
            goLower = !goLower;
            nextSwitch = timeF + TRANSITION_DURATION;
        }

        // Get direction
        float startValue = goLower ? upperY : lowerY;
        float c = goLower ? lowerY : upperY;

        return (int)easeOut(time, startValue, c, TRANSITION_DURATION);
    }

    /**
     * Cubic easing out.
     * @param t Current time
     * @param b Start value
     * @param c Change in value
     * @param d Duration
     * @return Position
     */
    public static float easeOut(float t, float b, float c, float d) {
        t /= d;
        t--;
        return c * (t * t * t + 1) + b;
    }
}
