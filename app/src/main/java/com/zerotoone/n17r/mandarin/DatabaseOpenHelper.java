package com.zerotoone.n17r.mandarin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Azat Kun on 7/31/2017.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper {

    public static final String DB_NAME = "quizMandarin.db";
    public static final int DB_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
