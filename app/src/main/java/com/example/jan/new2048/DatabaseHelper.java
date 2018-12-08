package com.example.jan.new2048;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "New2048.db";
    public static final String HIGHSCORES_TABLE_NAME = "highscores";
    public static final String HIGHSCORES_COLUMN_ID = "id";
    public static final String HIGHSCORES_COLUMN_NICK = "nick";
    public static final String HIGHSCORES_COLUMN_SCORE = "score";

    public static ArrayList<String> scoreList = new ArrayList<String>();

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE highscores " + "(id INTEGER PRIMARY KEY, name TEXT, type INTEGER, cost INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
