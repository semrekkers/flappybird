package com.hertogsem.flappybird;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Sem Rekkers on 18-1-2017.
 */

public class Score {
    public static final String TABLE_NAME = "score";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";

    public static final String[] COLUMNS = {
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_SCORE
    };

    public int id;
    public String name;
    public int score;

    public Score() {
        this.id = 0;
        this.name = null;
        this.score = 0;
    }

    public Score(Cursor c) {
        this.id = c.getInt(c.getColumnIndex(COLUMN_ID));
        this.name = c.getString(c.getColumnIndex(COLUMN_NAME));
        this.score = c.getInt(c.getColumnIndex(COLUMN_SCORE));
    }

    public void saveToDb(SQLiteDatabase db) throws Exception {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, this.name);
        values.put(COLUMN_SCORE, this.score);

        int id = (int)db.insert(TABLE_NAME, null, values);
        if (id == -1) {
            throw new Exception("Exception while inserting score");
        }
        this.id = id;
    }
}
