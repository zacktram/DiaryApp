package com.example.finalprojectcs373;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Console;

public class SignUpActivity extends AppCompatActivity {


    private EditText usernameInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        initWidgets();
    }

    public void initWidgets() {
        usernameInput = findViewById(R.id.usernameSignupInput);
        passwordInput = findViewById(R.id.passwordSignupInput);
    }

    public void ReturnToLogin(View v) {
        Intent intent = new Intent(this, StartingActivity.class);
        startActivity(intent);
    }

    public void SignUpUser(View v) {
        Intent i = new Intent(this, StartingActivity.class);

        ((DiaryApp) this.getApplication()).setUsername(String.valueOf(usernameInput.getText()).toLowerCase());
        ((DiaryApp) this.getApplication()).setPassword(String.valueOf(passwordInput.getText()));

        Log.d("globalVariable", ((DiaryApp) this.getApplication()).getUsername());
        Log.d("globalVariable", ((DiaryApp) this.getApplication()).getPassword());

        startActivity(i);
    }
}
