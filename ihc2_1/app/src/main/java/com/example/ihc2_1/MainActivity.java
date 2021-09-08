package com.example.ihc2_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor1, sensor2;

    EditText editTextLight, editTextGyro;
    Button GPSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLight = findViewById(R.id.sensor1);
        editTextGyro = findViewById(R.id.sensor2);
        GPSButton = findViewById(R.id.getGPSBtn);

        if (sensor1 != null) {
            sensorManager.registerListener(MainActivity.this , sensor1, SensorManager.SENSOR_DELAY_NORMAL );
        } else editTextLight.setText( "Light sensor not supported" );


        if(sensor2 != null) {
            sensorManager.registerListener(MainActivity.this , sensor2, SensorManager.SENSOR_DELAY_NORMAL );
        } else editTextGyro.setText( "Sensor not supported" );


        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        GPSButton.setOnClickListener(v -> {
                GPSTracker g = new GPSTracker(getApplicationContext());
                Location l = g.getLocation();
                if (l != null) {
                    double lat = l.getLatitude();
                    double longitude = l.getLongitude();
                    Toast.makeText(getApplicationContext(), "LAT: " + lat + "\nLONG: " +
                            longitude, Toast.LENGTH_LONG).show();
                }
        });
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor1, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_LIGHT) {
            editTextLight.setText("Light Intensity: " + event.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            editTextGyro.setText("Rotation X Axis: " + event.values[0] + " Axis Y: " + event.values[1] + " Axis Z: " + event.values[2]);
        }
    }
}