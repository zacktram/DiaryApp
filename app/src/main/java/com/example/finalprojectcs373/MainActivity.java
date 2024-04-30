package com.example.finalprojectcs373;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void SignUp(View v) {
        Intent intent = new Intent(MainActivity.this, SignUp.class);
        startActivity(intent);
    }

    public void VerifyUser(View v) {
        Intent intent = new Intent(MainActivity.this, HomePage.class);

        EditText usernameInput = (EditText)findViewById(R.id.usernameLoginInput);
        String username = usernameInput.getText().toString();

        EditText passwordInput = (EditText)findViewById(R.id.passwordLoginInput);
        String password = passwordInput.getText().toString();

        if (username.equals("admin") && password.equals("admin")) {
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(v, "Incorrect Username or Password", BaseTransientBottomBar.LENGTH_SHORT);
            snackbar.setAnchorView(findViewById(R.id.diaryText));
            snackbar.show();
        }
    }

}