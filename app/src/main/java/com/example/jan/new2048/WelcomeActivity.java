package com.example.jan.new2048;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WelcomeActivity extends Activity implements SensorEventListener{


    Dialog settingsDialog;

    Switch soundSwitch;
    Switch musicSwitch;

    Button backButton;

    MediaPlayer musicPlayer;
    MediaPlayer soundPlayer;

    TextView textX;
    TextView textY;
    TextView textZ;

    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;

    private Display mDisplay;

    private float[] mAccelerometerData = new float[3];
    private float[] mMagnetometerData = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        settingsDialog = new Dialog(this);

        musicPlayer = MediaPlayer.create(this,R.raw.gta);
        musicPlayer.setLooping(true);
        soundPlayer = MediaPlayer.create(this,R.raw.hit);

        textX = findViewById(R.id.textX);
        textY = findViewById(R.id.textY);
        textZ = findViewById(R.id.textZ);

//        mSensorManager = (SensorManager) getSystemService(
//                Context.SENSOR_SERVICE);
//        mSensorAccelerometer = mSensorManager.getDefaultSensor(
//                Sensor.TYPE_ACCELEROMETER);
//        mSensorMagnetometer = mSensorManager.getDefaultSensor(
//                Sensor.TYPE_MAGNETIC_FIELD);
//
//        // Get the display from the window manager (for rotation).
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        mDisplay = wm.getDefaultDisplay();
//
//
//        if (mSensorAccelerometer != null) {
//            mSensorManager.registerListener(this, mSensorAccelerometer,
//                    SensorManager.SENSOR_DELAY_NORMAL);
//        }
//        if (mSensorMagnetometer != null) {
//            mSensorManager.registerListener(this, mSensorMagnetometer,
//                    SensorManager.SENSOR_DELAY_NORMAL);
//        }

//        // Register sensor Listener
//        mSensorManager.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

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


    @Override
    public void onSensorChanged(SensorEvent event) {
//        textX.setText("X: " + event.values[0]);
//        textY.setText("Y: " + event.values[1]);
//        textZ.setText("Z: " + event.values[2]);


        // The sensor type (as defined in the Sensor class).
        int sensorType = event.sensor.getType();


        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetometerData = event.values.clone();
                break;
            default:
                return;
        }

        float[] rotationMatrix = new float[9];
        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix,
                null, mAccelerometerData, mMagnetometerData);

        // Remap the matrix based on current device/activity rotation.
        float[] rotationMatrixAdjusted = new float[9];
        switch (mDisplay.getRotation()) {
            case Surface.ROTATION_0:
                rotationMatrixAdjusted = rotationMatrix.clone();
                break;
            case Surface.ROTATION_90:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_180:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_270:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X,
                        rotationMatrixAdjusted);
                break;
        }

        // Get the orientation of the device (azimuth, pitch, roll) based
        // on the rotation matrix. Output units are radians.
        float orientationValues[] = new float[3];
        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrixAdjusted,
                    orientationValues);
        }

        // Pull out the individual values from the array.
        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];



        textX.setText("Azimuth: " + azimuth);
        textY.setText("Pitch: " + pitch);
        textZ.setText("Roll: " + roll);


//        // Pitch and roll values that are close to but not 0 cause the
//        // animation to flash a lot. Adjust pitch and roll to 0 for very
//        // small values (as defined by VALUE_DRIFT).
//        if (Math.abs(pitch) < VALUE_DRIFT) {
//            pitch = 0;
//        }
//        if (Math.abs(roll) < VALUE_DRIFT) {
//            roll = 0;
//        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
