package com.example.jan.new2048;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView scoreLabel;
    CanvasView canvasView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setBackgroundColor(Color.GRAY);

        scoreLabel = findViewById(R.id.scoreLabel);
        canvasView = findViewById(R.id.canvasView);

        canvasView.setScoreLabel(scoreLabel);
        canvasView.setBackgroundColor(Color.LTGRAY);

    }

}
