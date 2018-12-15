package com.example.jan.new2048;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView scoreLabel;
    CanvasView canvasView;

    Dialog saveDialog;
    Button saveButton;
    Button cancelButton;
    EditText editText;

    Dialog settingsDialog;
    Button backButton;

    private DatabaseHelper myDb;

    private MediaPlayer musicPlayer;
    private MediaPlayer soundPlayer;

    boolean playMusic = false;
    boolean playSounds = false;

    Switch soundSwitch;
    Switch musicSwitch;
    Switch motionControlsSwitch;

    public void setPlayMusic(boolean music){
        this.playMusic = music;
    }

    public void setPlaySounds(boolean sounds){
        this.playSounds = sounds;
    }

    public void setMusicPlayer(MediaPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveDialog = new Dialog(this);

        settingsDialog = new Dialog(this);
        settingsDialog.setContentView(R.layout.settings_popup);

        getWindow().getDecorView().setBackgroundColor(Color.LTGRAY);

        scoreLabel = findViewById(R.id.scoreLabel);
        canvasView = findViewById(R.id.canvasView);


//        canvasView.setBackgroundColor(Color.LTGRAY);

        this.myDb = new DatabaseHelper(this);

        musicPlayer = MediaPlayer.create(this,R.raw.mountainking);
        musicPlayer.setLooping(true);
        soundPlayer = MediaPlayer.create(this,R.raw.hit);

        canvasView.setScoreLabel(scoreLabel);
        canvasView.setPlaySounds(playSounds);
        canvasView.setSoundPlayer(soundPlayer);


//        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    Toast.makeText(getApplicationContext(),"Sound switch is checked",Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(),"Sound switch is not checked",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

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
            editText = (EditText) saveDialog.findViewById(R.id.editText);
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
                    int score = canvasView.getScore();

//                    Toast.makeText(getApplicationContext(), "Save clicked", Toast.LENGTH_SHORT).show();
                    if(myDb.insertScore(nick,score)){
                        Toast.makeText(getApplicationContext(), "Score saved " + nick + " " + score, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Score was not saved", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(intent);
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
        if(myId == R.id.settingsItem){

            backButton = (Button)settingsDialog.findViewById(R.id.settingsBackButton);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    settingsDialog.dismiss();
                }
            });

            soundSwitch = (Switch) settingsDialog.findViewById(R.id.soundsSwitch);
            musicSwitch = (Switch) settingsDialog.findViewById(R.id.musicSwitch);
            motionControlsSwitch = (Switch) settingsDialog.findViewById(R.id.motionSwitch);

            if (soundSwitch != null) {
                soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            Toast.makeText(getApplicationContext(),"Sound switch is checked",Toast.LENGTH_SHORT).show();
                            playSounds = true;
                            canvasView.setPlaySounds(playSounds);
                        } else {
                            Toast.makeText(getApplicationContext(),"Sound switch is not checked",Toast.LENGTH_SHORT).show();
                            playSounds = false;
                            canvasView.setPlaySounds(playSounds);
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
                            playMusic = true;
                            musicPlayer.start();
                        } else {
                            Toast.makeText(getApplicationContext(),"Music switch is not checked",Toast.LENGTH_SHORT).show();
                            playMusic = false;
                            musicPlayer.pause();
                        }
                    }
                });
            }

            if(motionControlsSwitch != null){
                motionControlsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            canvasView.setMotionControls(true);
                        } else{
                            canvasView.setMotionControls(false);
                        }
                    }
                });
            }


            settingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            settingsDialog.show();
        }

        return super.onOptionsItemSelected(item);
    }


}
