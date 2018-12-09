package com.example.jan.new2048;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toMap;

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

        ArrayList<String> newScoreList = new ArrayList<>();

        Map<String, Integer> sortedScores = sortByValue(mydb.getAllScores());


        for (Object key : sortedScores.keySet()){
            newScoreList.add((String)key + " " + mydb.getAllScores().get(key));
        }

        ArrayList arrayList = mydb.getAllScoresNick();

        Toast.makeText(getApplicationContext(), "All scores " + newScoreList.toString(), Toast.LENGTH_SHORT).show();


        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, newScoreList);

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

    private Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        //classic iterator example
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
        }*/


        return sortedMap;
    }
}
