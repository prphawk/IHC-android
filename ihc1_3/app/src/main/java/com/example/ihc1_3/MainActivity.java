package com.example.ihc1_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accelerometer;

    EditText editTextX;
    EditText editTextY;
    EditText editTextZ;

    float sensorValueX = 0.0f;
    float sensorValueY = 0.0f;
    float sensorValueZ = 0.0f;

    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editTextX = findViewById(R.id.editTextX);
        editTextY = findViewById(R.id.editTextY);
        editTextZ = findViewById(R.id.editTextZ);
    }

    public void beginSecondActivity() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {

        if(significantDiff(sensorValueX,event.values[0], "X") ||
                significantDiff(sensorValueY,event.values[1], "Y") ||
                significantDiff(sensorValueZ,event.values[2], "Z")
        ) beginSecondActivity();


            float sensorValueX = event.values[0];
            float sensorValueY = event.values[1];
            float sensorValueZ = event.values[2];

            editTextX.setText(String.valueOf(sensorValueX));
            editTextY.setText(String.valueOf(sensorValueY));
            editTextZ.setText(String.valueOf(sensorValueZ));
    }

    private boolean significantDiff(float value1, float value2, String axis) {
        float diff = Math.abs(value1 - value2);
        boolean isSignificant = diff >= 12.0f;
        if(isSignificant) {
            message = "Too quick on axis " + axis + "! Difference of " + diff;
        }
        return isSignificant;
    }
}