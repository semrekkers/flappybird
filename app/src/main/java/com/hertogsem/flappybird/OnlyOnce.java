package com.hertogsem.flappybird;

/**
 * Created by Sem Rekkers on 16-1-2017.
 */

public class OnlyOnce {

    private boolean alreadyRan;

    public OnlyOnce() {
        alreadyRan = false;
    }

    public void run(Method method) {
        synchronized (this) {
            if (!alreadyRan) {
                method.run();
            }
        }
    }

    public interface Method {
        void run();
    }
}
