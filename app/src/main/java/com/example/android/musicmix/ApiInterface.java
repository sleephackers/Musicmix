package com.example.android.musicmix;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("chart.tracks.get")
    abstract Call<TrackResponse> getTopTracks(@Query("apikey") String apikey);
}
