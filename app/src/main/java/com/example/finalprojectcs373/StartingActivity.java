package com.example.finalprojectcs373;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class StartingActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        initWidgets();
        setContentView(R.layout.starting_activity);
    }

    private void initWidgets() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    public void SignUp(View v) {
        Intent intent = new Intent(StartingActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public void VerifyUser(View v) {
        Intent intent = new Intent(StartingActivity.this, HomeActivity.class);

        EditText usernameInput = (EditText) findViewById(R.id.usernameLoginInput);
        String username = usernameInput.getText().toString();

        EditText passwordInput = (EditText) findViewById(R.id.passwordLoginInput);
        String password = passwordInput.getText().toString();

        if (username.toLowerCase().equals(getUsername()) && password.equals(getPassword())) {
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(v, "Incorrect Username or Password", BaseTransientBottomBar.LENGTH_SHORT);
            snackbar.setAnchorView(findViewById(R.id.diaryText));
            snackbar.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float lightValue = sensorEvent.values[0];
        if(lightValue > 20000) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public String getUsername() {
       return ((DiaryApp) this.getApplication()).getUsername();
    }

    public String getPassword() {

        return ((DiaryApp) this.getApplication()).getPassword();

    }
}