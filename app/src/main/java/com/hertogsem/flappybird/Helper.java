package com.hertogsem.flappybird;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Sem Rekkers on 13-1-2017.
 */

public class Helper {
    public static final String TAG = "Helper";

    public static Bitmap rotateBitmap180(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();
        Matrix matrix = new Matrix();

        matrix.postRotate(180);
        return Bitmap.createBitmap(src, 0, 0, width, height, matrix, true);
    }

}
