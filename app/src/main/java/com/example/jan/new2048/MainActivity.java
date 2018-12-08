package com.example.jan.new2048;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    TextView scoreLabel;
    CanvasView canvasView;

    Dialog saveDialog;
    Button saveButton;
    Button cancelButton;
    EditText editText;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveDialog = new Dialog(this);

        getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);

        scoreLabel = findViewById(R.id.scoreLabel);
        canvasView = findViewById(R.id.canvasView);

        canvasView.setScoreLabel(scoreLabel);
//        canvasView.setBackgroundColor(Color.LTGRAY);

    }

    public void showDialog(View v){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int myId = item.getItemId();

        if(myId == R.id.saveScoreItem){

            saveDialog.setContentView(R.layout.custom_popup);
            editText = (EditText) findViewById(R.id.editText);
            cancelButton = (Button)saveDialog.findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveDialog.dismiss();
                }
            });
            saveButton = (Button)saveDialog.findViewById(R.id.saveButton);
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nick = editText.getText().toString();
                    //int score = canvasView.getScore();

                    if(myDb.insertScore(nick,10)){
                        Toast.makeText(getApplicationContext(), "Score saved", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Score was not saved", Toast.LENGTH_SHORT).show();
                    }

                    //Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                    //startActivity(intent);
                }
            });


            saveDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            saveDialog.show();

//            Intent intent = new Intent(this, aboutActivity.class);
//            startActivity(intent);
        }
        if(myId == R.id.exitItem){

            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
