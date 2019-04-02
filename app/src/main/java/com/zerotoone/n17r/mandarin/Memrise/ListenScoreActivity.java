package com.zerotoone.n17r.mandarin.Memrise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zerotoone.n17r.mandarin.R;

public class ListenScoreActivity extends AppCompatActivity {

    TextView mCorrect, mWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_score);
        mCorrect = (TextView) findViewById(R.id.correctNum);
        mWrong = (TextView) findViewById(R.id.wrongNum);

        findViewById(R.id.tryAgainBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListenScoreActivity.this, LearnActivity.class));
            }
        });

        Intent intent = getIntent();
        int score = intent.getIntExtra("score2", 0);

        mCorrect.setText(String.valueOf(score));
        mWrong.setText(String.valueOf(5-score));

    }
}
