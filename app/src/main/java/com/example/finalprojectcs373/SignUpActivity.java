package com.example.finalprojectcs373;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
    }

    public void ReturnToLogin(View v) {
        Intent intent = new Intent(this, StartingActivity.class);
        startActivity(intent);
    }
}
