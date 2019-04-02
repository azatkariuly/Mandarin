package com.zerotoone.n17r.mandarin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azat Kun on 7/31/2017.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_QUESTION = "questionBank.db";
    private  static final int DATABASE_VERSION = 1;

    public static final String TABLE_QUESTION = "QuestionBank";

    public static final String KEY_ID = "id";
    private static final String QUESTION = "question";
    public static final String PINYIN = "pinyin";
    public static final String ANSWER = "answer";
    public static final String EXAMPLE = "example";
    public static final String TRANSLATION = "trans";

    private static final String CREATE_TABLE_QUESTION = "CREATE TABLE "
            + TABLE_QUESTION + " (" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT,"
            + PINYIN + " TEXT, " + ANSWER + " TEXT, " + EXAMPLE + " TEXT, " + TRANSLATION + " TEXT);";


    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_QUESTION, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUESTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_QUESTION);
        onCreate(db);
    }

    public void addWords(List<Question> questions) {
        SQLiteDatabase db = this.getWritableDatabase();

        for(int i=0; i < questions.size(); i++){
            ContentValues values = new ContentValues();

            values.put(QUESTION, questions.get(i).getQuestion());
            values.put(PINYIN, questions.get(i).getPinyin());
            values.put(ANSWER, questions.get(i).getAnswer());
            values.put(EXAMPLE, questions.get(i).getExample());
            values.put(TRANSLATION, questions.get(i).getTrans());

            db.insert(TABLE_QUESTION, null, values);
        }

    }

    public List<Question> getAllQuestionList() {
        List<Question> questionArrayList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_QUESTION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();

                String questText = c.getString(c.getColumnIndex(QUESTION));
                question.setQuestion(questText);

                String pinyinText = c.getString(c.getColumnIndex(PINYIN));
                question.setPinyin(pinyinText);

                String answerText = c.getString(c.getColumnIndex(ANSWER));
                question.setAnswer(answerText);

                String exampleText = c.getString(c.getColumnIndex(EXAMPLE));
                question.setExample(exampleText);

                String transText = c.getString(c.getColumnIndex(TRANSLATION));
                question.setTrans(transText);

                questionArrayList.add(question);

            } while (c.moveToNext());
        }
        return questionArrayList;
    }

}
