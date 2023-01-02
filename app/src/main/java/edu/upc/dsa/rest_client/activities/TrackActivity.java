package edu.upc.dsa.rest_client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import edu.upc.dsa.rest_client.Api;
import edu.upc.dsa.rest_client.R;
import edu.upc.dsa.rest_client.RetrofitClient;
import edu.upc.dsa.rest_client.models.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackActivity extends AppCompatActivity {
    Api APIservice;

    String id;
    String title;
    String singer;

    TextInputEditText titleInput;
    TextInputEditText singerInput;

    TextView idTxt;
    TextView titleTxt;
    TextView singerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        APIservice = RetrofitClient.getInstance().getMyApi();

        titleInput = (TextInputEditText) findViewById(R.id.titleInput);
        singerInput = (TextInputEditText) findViewById(R.id.singerInput);
        idTxt = (TextView) findViewById(R.id.idTxt);
        titleTxt = (TextView) findViewById(R.id.titleTxt);
        singerTxt = (TextView) findViewById(R.id.singerTxt);

        Bundle content = getIntent().getExtras();
        if(content != null){
            this.id = content.getString("id");
            this.title = content.getString("title");
            this.singer = content.getString("singer");
            updateTextViews();
        }
    }

    public void applyChanges(View view) {
        if(!titleInput.getText().toString().equals(""))
            title = titleInput.getText().toString();
        if(!singerInput.getText().toString().equals(""))
            singer = singerInput.getText().toString();

        Call<Void> call = APIservice.updateTrack(new Track(id, title, singer));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });

        updateTextViews();
    }

    private void updateTextViews(){
        idTxt.setText(this.id);
        titleTxt.setText(this.title);
        singerTxt.setText(this.singer);
    }

    public void returnTracks(View view){
        Intent intentTracks = new Intent(TrackActivity.this, TrackListActivity.class);
        TrackActivity.this.startActivity(intentTracks);
    }
}
