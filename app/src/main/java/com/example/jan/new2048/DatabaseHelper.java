package com.example.jan.new2048;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        db.execSQL("DROP TABLE IF EXISTS highscores");
        onCreate(db);
    }

    public boolean insertScore(String nick, int score)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(HIGHSCORES_COLUMN_NICK, nick);
        contentValues.put(HIGHSCORES_COLUMN_SCORE, score);
        db.insert(HIGHSCORES_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from highscores where id=" + id + "", null);
        return res;
    }

    public void setAllScores()
    {
        scoreList.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from highscores", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            scoreList.add(res.getString(res.getColumnIndex(HIGHSCORES_COLUMN_NICK)));
            res.moveToNext();
        }
    }

    public ArrayList<String> getAllScoresNick()
    {
        return scoreList;
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HIGHSCORES_TABLE_NAME, "1", null);
    }
}
