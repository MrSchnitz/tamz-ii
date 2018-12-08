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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView scoreLabel;
    CanvasView canvasView;

    Dialog saveDialog;
    Button saveButton;
    Button cancelButton;

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
            cancelButton = (Button)saveDialog.findViewById(R.id.cancelButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveDialog.dismiss();
                }
            });

            saveDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            saveDialog.show();

            Toast.makeText(getApplicationContext(),"Save score id id: " + myId,Toast.LENGTH_LONG).show();

//            Intent intent = new Intent(this, aboutActivity.class);
//            startActivity(intent);
        }
        if(myId == R.id.exitItem){
            Toast.makeText(getApplicationContext(),"Exit id: " + myId,Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
