package com.example.jan.new2048;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HighscoreActivity extends Activity {

    DatabaseHelper mydb;
    private ListView scoreList;
    public static ArrayList<Long> arrayListNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        mydb = new DatabaseHelper(this);

        mydb.setAllScores();

        ArrayList arrayList = mydb.getAllScoresNick();

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrayList);

        scoreList = (ListView)findViewById(R.id.highscoreList);
        scoreList.setAdapter(arrayAdapter);
        scoreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                int id_To_Search = arg2+1;
                Log.d("Clicked item id", " "+ arg2);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

//                Intent intent = new Intent(getApplicationContext(), DisplayContact.class);
//                intent.putExtras(dataBundle);
//                startActivity(intent);
            }
        });
    }
}
