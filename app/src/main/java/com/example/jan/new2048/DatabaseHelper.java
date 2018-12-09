package com.example.jan.new2048;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "New2048.db";
    public static final String HIGHSCORES_TABLE_NAME = "highscores";
    public static final String HIGHSCORES_COLUMN_ID = "id";
    public static final String HIGHSCORES_COLUMN_NICK = "nick";
    public static final String HIGHSCORES_COLUMN_SCORE = "score";

    public static ArrayList<String> scoreList = new ArrayList<String>();

    public static Map<String, Integer> scoreMap = new HashMap();

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE highscores " + "(id INTEGER PRIMARY KEY, nick TEXT, score INTEGER)");
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
        scoreMap.clear();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from highscores", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            scoreMap.put(res.getString(res.getColumnIndex(HIGHSCORES_COLUMN_NICK)),res.getInt(res.getColumnIndex(HIGHSCORES_COLUMN_SCORE)));
            scoreList.add(res.getString(res.getColumnIndex(HIGHSCORES_COLUMN_NICK)));
            res.moveToNext();
        }
    }

    public ArrayList<String> getAllScoresNick()
    {
        return scoreList;
    }

    public Map getAllScores(){
        return scoreMap;
    }

    public void removeAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(HIGHSCORES_TABLE_NAME, "1", null);
    }
}
