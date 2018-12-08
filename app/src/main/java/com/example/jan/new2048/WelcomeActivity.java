package com.example.jan.new2048;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class WelcomeActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void newGameClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("theme",theme);
        startActivity(intent);
    }


    public void highscoreClick(View view) {
        Intent intent = new Intent(this, HighscoreActivity.class);
        startActivity(intent);
    }

    public void quitClick(View view) {
        finishAndRemoveTask();
    }
}
