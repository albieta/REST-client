package edu.upc.dsa.rest_client.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import edu.upc.dsa.rest_client.Api;
import edu.upc.dsa.rest_client.R;
import edu.upc.dsa.rest_client.RetrofitClient;
import edu.upc.dsa.rest_client.models.Track;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackListActivity extends AppCompatActivity implements RecyclerViewClickListener {
    Api APIservice;

    private RecyclerView recyclerViewTracks;
    private RecyclerViewAdapter adapterTracks;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.track_list);
        recyclerViewTracks = (RecyclerView) findViewById(R.id.recyclerTrack);
        Log.d("DDDD", "" + recyclerViewTracks);
        recyclerViewTracks.setLayoutManager(new LinearLayoutManager(this));

        APIservice = RetrofitClient.getInstance().getMyApi();
        Call<List<Track>> call = APIservice.getTracks();
        try {
            adapterTracks = new RecyclerViewAdapter(call.execute().body(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recyclerViewTracks.setAdapter(adapterTracks);
    }

    public void deleteTrack(View view) {
        Call<Void> call = APIservice.deleteTrack(view.getTag().toString());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {}

            @Override
            public void onFailure(Call<Void> call, Throwable t) {}
        });

        adapterTracks.deleteTrack(view.getTag().toString());
    }

    @Override
    public void recyclerViewListClicked(View view, int position) {
        Track track = adapterTracks.tracks.get(position);
        Intent intentTrack = new Intent(TrackListActivity.this, TrackActivity.class);
        Bundle adapterInfo = new Bundle();
        adapterInfo.putString("id", track.getId());
        adapterInfo.putString("title", track.getTitle());
        adapterInfo.putString("singer", track.getSinger());
        intentTrack.putExtras(adapterInfo);
        TrackListActivity.this.startActivity(intentTrack);
    }

    public void returnMain(View view){
        Intent intentMain = new Intent(TrackListActivity.this, MainActivity.class);
        TrackListActivity.this.startActivity(intentMain);
    }

    public void addTrack(View view) {
        Intent intentAddTrack = new Intent(TrackListActivity.this, AddTrackActivity.class);
        TrackListActivity.this.startActivity(intentAddTrack);
    }
}
