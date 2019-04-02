package com.zerotoone.n17r.mandarin.Memrise;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zerotoone.n17r.mandarin.Question;
import com.zerotoone.n17r.mandarin.R;

import java.util.List;

public class LearnActivity extends AppCompatActivity {

    WordsDatabaseOpenHelper wordsDatabase;
    List<Question> list;


    TextView mQuestion;
    EditText mAnswerEditTxt;
    Button mAnswerBtn;


    int mQuestionNumber = 0;
    private int mScore = 0;
    String mAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mQuestion = (TextView) findViewById(R.id.questionLearn);
        mAnswerEditTxt = (EditText) findViewById(R.id.answerLearn);
        mAnswerBtn = (Button) findViewById(R.id.answerBtn);


        wordsDatabase = new WordsDatabaseOpenHelper(this);
        list = wordsDatabase.getAllQuestionList();



        updateQuestion();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);

    }


    public void updateQuestion() {
        if(mQuestionNumber<list.size()) {
            mQuestion.setText(list.get(mQuestionNumber).getQuestion());
            mAnswer = list.get(mQuestionNumber).getAnswer();
            mQuestionNumber++;

        } else {
            Intent intent = new Intent(LearnActivity.this, LearnScoreActivity.class);
            intent.putExtra("score1", mScore);
            startActivity(intent);

        }
    }


    public void onClick(View view) {

        if(mAnswerEditTxt.getText().toString().equals(mAnswer)) {
            mScore++;
            Toast.makeText(LearnActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LearnActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
        }

        updateQuestion();
        mAnswerEditTxt.getText().clear();
    }
}
