package edu.upc.dsa.rest_client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.upc.dsa.rest_client.R;

public class MainActivity extends AppCompatActivity {

    Button registerBtn;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

    }

    public void btnClicked(View view) throws IOException {
        if(view==registerBtn){
            Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
            MainActivity.this.startActivity(intentRegister);
        }
        if(view==loginBtn){
            Intent intentRegister = new Intent(MainActivity.this, LogInActivity.class);
            MainActivity.this.startActivity(intentRegister);
        }
    }
}
