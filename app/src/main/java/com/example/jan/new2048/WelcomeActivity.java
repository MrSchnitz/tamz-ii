package com.example.jan.new2048;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends Activity {


    Dialog settingsDialog;

    Switch soundSwitch;
    Switch musicSwitch;

    Button backButton;

    MediaPlayer musicPlayer;
    MediaPlayer soundPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        settingsDialog = new Dialog(this);

        musicPlayer = MediaPlayer.create(this,R.raw.gta);
        musicPlayer.setLooping(true);
        soundPlayer = MediaPlayer.create(this,R.raw.hit);
        
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

    public void settingsClick(View view) {
        settingsDialog.setContentView(R.layout.settings_popup);

        backButton = (Button)settingsDialog.findViewById(R.id.settingsBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialog.dismiss();
            }
        });

        soundSwitch = (Switch) settingsDialog.findViewById(R.id.soundsSwitch);
        musicSwitch = (Switch) settingsDialog.findViewById(R.id.musicSwitch);

        if (soundSwitch != null) {
            soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),"Sound switch is checked",Toast.LENGTH_SHORT).show();
//                        playSounds = true;
//                        canvasView.setPlaySounds(playSounds);

                    } else {
                        Toast.makeText(getApplicationContext(),"Sound switch is not checked",Toast.LENGTH_SHORT).show();
//                        playSounds = false;
//                        canvasView.setPlaySounds(playSounds);
                    }
                }
            });
        }

        if(musicSwitch != null){
            musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Toast.makeText(getApplicationContext(),"Music switch is checked",Toast.LENGTH_SHORT).show();
//                        playMusic = true;
//                        musicPlayer.start();
                    } else {
                        Toast.makeText(getApplicationContext(),"Music switch is not checked",Toast.LENGTH_SHORT).show();
//                        playMusic = false;
//                        musicPlayer.pause();
                    }
                }
            });
        }


        settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        settingsDialog.show();
    }
}
