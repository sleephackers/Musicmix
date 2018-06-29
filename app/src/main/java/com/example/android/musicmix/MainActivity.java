package com.example.android.musicmix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Track> tracks;
    private RecyclerAdapter adapter;
    private ApiInterface apiInterface;
    private final static String API_KEY = "0ce449cc4ebd0c6c62b0cf1903fe0830";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        layoutManager= new LinearLayoutManager(this);
        adapter= new RecyclerAdapter(tracks,MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchInformation();
    }

    public void fetchInformation()
    {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Track>> call=apiInterface.getTrackInfo(API_KEY);
        call.enqueue(new Callback<List<Track>>() {
            @Override
            public void onResponse(Call<List<Track>> call, Response<List<Track>> response) {
                tracks=response.body();
                adapter= new RecyclerAdapter(tracks,MainActivity.this);
                recyclerView.setAdapter(adapter);
                Log.e(TAG, "Number of tracks received: " + tracks.size());
            }

            @Override
            public void onFailure(Call<List<Track>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
