package com.zerotoone.n17r.mandarin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Azat Kun on 7/31/2017.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if(database != null) {
            this.database.close();
        }
    }

    public List<Question> getAllQuestions() {
        List<Question> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM lesson", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            Question question = new Question();

            question.setQuestion(cursor.getString(1));
            question.setPinyin(cursor.getString(2));
            question.setAnswer(cursor.getString(3));
            question.setExample(cursor.getString(4));
            question.setTrans(cursor.getString(5));

            list.add(question);
            cursor.moveToNext();
        }

        Collections.shuffle(list);

        cursor.close();
        return list;
    }


}
