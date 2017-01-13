package com.hertogsem.flappybird;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sem Rekkers on 13-1-2017.
 */

public class Helper {
    public static final String TAG = "Helper";

    public static Bitmap getBitmapFromAsset(Context context, String filePath) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream input = null;
        Bitmap bitmap;

        Log.i(TAG, "Loading Bitmap asset "+filePath);
        try {
            input = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(input);
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            // close the input stream when it's open
            if (input != null) {
                input.close();
            }
        }

        return bitmap;
    }

}
