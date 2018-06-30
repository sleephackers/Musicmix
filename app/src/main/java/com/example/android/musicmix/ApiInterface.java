package com.example.android.musicmix;

import com.example.android.musicmix.TopTracks.TrackResponse;
import com.example.android.musicmix.TrackInfoId.TrackInfoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("chart.tracks.get")
    Call<TrackResponse> getTopTracks(@Query("apikey") String apikey);

    @GET("track.get")
    Call<TrackInfoResponse> getTrackInfo(@Query("track_id") Integer id,
                                         @Query("apikey") String apikey);

}
