package com.example.jan.new2048;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    }

    public void quitClick(View view) {
        finishAndRemoveTask();
    }
}
