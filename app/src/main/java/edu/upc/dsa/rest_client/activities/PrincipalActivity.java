package edu.upc.dsa.rest_client.activities;

import static edu.upc.dsa.rest_client.activities.LogInActivity.SHARED_PREFS;
import static edu.upc.dsa.rest_client.activities.LogInActivity.TEXT1;
import static edu.upc.dsa.rest_client.activities.LogInActivity.TEXT2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import edu.upc.dsa.rest_client.Api;
import edu.upc.dsa.rest_client.R;

public class PrincipalActivity extends AppCompatActivity {

    Api APIservice;

    private String text1;
    private String text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_main);
        loadData();

        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                if(!Objects.equals(text1, "") && !Objects.equals(text2, "")) {
                    Intent intent = new Intent(PrincipalActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(PrincipalActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(t,5000);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text1 = sharedPreferences.getString(TEXT1, "");
        text2 = sharedPreferences.getString(TEXT2, "");
    }
}