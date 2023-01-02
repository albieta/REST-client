package edu.upc.dsa.rest_client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.dsa.rest_client.R;

public class MainActivity extends AppCompatActivity {

    Button tracksBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tracksBtn = (Button) findViewById(R.id.tracksBtn);
    }

    public void btnClicked(View view) {
        if(view==tracksBtn){
            Intent intentRegister = new Intent(MainActivity.this, TrackListActivity.class);
            MainActivity.this.startActivity(intentRegister);
        }
    }
}
