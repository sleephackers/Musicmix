package com.example.android.musicmix;

import com.example.android.musicmix.ArtistInfoId.ArtistInfoResponse;
import com.example.android.musicmix.TopArtists.ArtistResponse;
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

    @GET("chart.artists.get")
    Call<ArtistResponse> getTopArtists(@Query("apikey") String apikey);

    @GET("artist.get")
    Call<ArtistInfoResponse> getArtistInfo(@Query("artist_id") Integer id,
                                           @Query("apikey") String apikey);
}
