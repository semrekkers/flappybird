package com.hertogsem.flappybird;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sem Rekkers on 18-1-2017.
 */

public class ScoreDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "score.db";
    public static final int DATABASE_VERSION = 1;

    public ScoreDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_SETUP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        deleteEntries();
        onCreate(db);
    }

    public static Cursor selectScores(SQLiteDatabase db) {
        return db.query(Score.TABLE_NAME, Score.COLUMNS, null, null, null, null, Score.COLUMN_SCORE+" DESC", null);
    }

    public void deleteEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        db.close();
    }

    private static final String SQL_SETUP =
            "CREATE TABLE `score` (\n" +
                    "\t`_id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t`name`\tTEXT NOT NULL,\n" +
                    "\t`score`\tINTEGER NOT NULL\n" +
                    ");\n" +
            "CREATE INDEX `index_score_name` ON `score` (`name` ASC);\n" +
            "CREATE INDEX `index_score_score` ON `score` (`score` DESC);";
    private static final String SQL_DELETE_ENTRIES = "DELETE FROM `score`;";
}
