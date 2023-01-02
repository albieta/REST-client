package edu.upc.dsa.rest_client;

import java.util.List;

import edu.upc.dsa.rest_client.models.Track;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @GET("/tracks")
    Call<List<Track>> getTracks();

    @POST("/tracks")
    Call<Track> createTrack(@Body Track track);

    @PUT("/tracks")
    Call<Void> updateTrack(@Body Track track);

    @DELETE("/tracks/{id}")
    Call<Void> deleteTrack(@Path("id") String id);
}
