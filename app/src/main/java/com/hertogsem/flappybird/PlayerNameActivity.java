package com.hertogsem.flappybird;

import android.content.Intent;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayerNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_name);

        final Intent intent = getIntent();
        Button button = (Button) findViewById(R.id.okButton);
        final EditText nameField = (EditText) findViewById(R.id.playerNameField);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextIntent = new Intent(getBaseContext(), ScoreboardActivity.class);
                nextIntent.putExtra(ScoreboardActivity.EXTRA_NAME, nameField.getText().toString());
                nextIntent.putExtra(ScoreboardActivity.EXTRA_SCORE, intent.getIntExtra(ScoreboardActivity.EXTRA_SCORE, -1));
                startActivity(nextIntent);
            }
        });
    }
}
