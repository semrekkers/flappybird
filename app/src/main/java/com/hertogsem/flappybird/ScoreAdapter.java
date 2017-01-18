package com.hertogsem.flappybird;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Sem Rekkers on 18-1-2017.
 */

public class ScoreAdapter extends CursorAdapter {

    public ScoreAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Get TextViews
        TextView playerName = (TextView) view.findViewById(android.R.id.text1);
        TextView scoreField = (TextView) view.findViewById(android.R.id.text2);

        // Fill TextViews
        Score score = new Score(cursor);
        playerName.setText(score.name);
        scoreField.setText("Score: " + score.score);
    }

}
