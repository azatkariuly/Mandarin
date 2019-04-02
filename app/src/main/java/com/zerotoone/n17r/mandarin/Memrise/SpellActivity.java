package com.zerotoone.n17r.mandarin.Memrise;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
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
import java.util.Locale;

public class SpellActivity extends AppCompatActivity implements View.OnClickListener{

    TextToSpeech t1;

    EditText mEditText;
    TextView mPinyinTxt;

    String mQuestion;
    String mAnswer;
    int mQuestionNumber = 0;
    private int mScore = 0;

    WordsDatabaseOpenHelper wordsDatabase;
    List<Question> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mEditText = (EditText) findViewById(R.id.answerSpell);
        mPinyinTxt = (TextView) findViewById(R.id.pinyinSpell);


        findViewById(R.id.imageSpell).setOnClickListener(this);
        findViewById(R.id.answerSpellBtn).setOnClickListener(this);

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
            mPinyinTxt.setText(list.get(mQuestionNumber).getPinyin());
            mAnswer = list.get(mQuestionNumber).getAnswer();

            mQuestion = list.get(mQuestionNumber).getQuestion();

            t1=new TextToSpeech(SpellActivity.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.CHINA);
                        t1.speak(mQuestion, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });

            mQuestionNumber++;






        } else {
            Intent intent = new Intent(SpellActivity.this, LearnScoreActivity.class);
            intent.putExtra("score2", mScore);
            startActivity(intent);

        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.answerSpellBtn) {

            if(mEditText.getText().toString().equals(mAnswer)) {
                mScore++;
                Toast.makeText(SpellActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SpellActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
            }

            updateQuestion();
            mEditText.getText().clear();
            updateQuestion();

        } else if (v.getId() == R.id.imageSpell) {
            t1=new TextToSpeech(v.getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if(status != TextToSpeech.ERROR) {
                        t1.setLanguage(Locale.CHINA);
                        t1.speak(mQuestion, TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
            });
        }


    }
}
