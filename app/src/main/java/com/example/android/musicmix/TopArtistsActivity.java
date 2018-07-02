package com.example.android.musicmix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.android.musicmix.RetrofitCalls.ApiClient;
import com.example.android.musicmix.RetrofitCalls.ApiInterface;
import com.example.android.musicmix.TopArtists.ArtistList;
import com.example.android.musicmix.TopArtists.ArtistResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopArtistsActivity extends AppCompatActivity {
    private final static String API_KEY = "4628d54512e015d8ce17b12be8fa1e70";
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ArtistList> artistList;
    private RecyclerAdapterArtist adapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_artists);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewArtists);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchArtists();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(TopArtistsActivity.this, ArtistInfo.class);
                Bundle extras = new Bundle();
                extras.putInt("id", artistList.get(position).getArtist().getArtistId());
                intent.putExtras(extras);
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));


    }

    public void fetchArtists() {


        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArtistResponse> call = apiInterface.getTopArtists(API_KEY);

        call.enqueue(new Callback<ArtistResponse>() {
            @Override
            public void onResponse(Call<ArtistResponse> call, Response<ArtistResponse> response) {
                artistList = response.body().getMessage().getBody().getArtistList();
                adapter = new RecyclerAdapterArtist(artistList, TopArtistsActivity.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArtistResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

}
