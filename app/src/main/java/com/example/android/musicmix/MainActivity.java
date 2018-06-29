package com.example.android.musicmix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<TrackList> trackList;
    private RecyclerAdapter adapter;
    private ApiInterface apiInterface;
    private final static String API_KEY ="4628d54512e015d8ce17b12be8fa1e70";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

      
        fetchInformation();

    }

    public void fetchInformation()
    {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<TrackResponse> call=apiInterface.getTopTracks(API_KEY);

        call.enqueue(new Callback<TrackResponse> () {
            @Override
            public void onResponse(Call<TrackResponse> call, Response<TrackResponse> response) {
                trackList=response.body().getMessage().getBody().getTrackList();


                adapter= new RecyclerAdapter(trackList,MainActivity.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<TrackResponse> call, Throwable t) {
                Log.e(TAG, "Hello6");

                Log.e(TAG, t.toString());
            }
        });
    }
}
