package com.example.android.musicmix;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.musicmix.Favourites.FavouritesActivity;
import com.example.android.musicmix.RetrofitCalls.ApiClient;
import com.example.android.musicmix.RetrofitCalls.ApiInterface;
import com.example.android.musicmix.Search.SearchActivity;
import com.example.android.musicmix.TopTracks.TrackList;
import com.example.android.musicmix.TopTracks.TrackResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<TrackList> trackList;
    private RecyclerAdapter adapter;
    private ApiInterface apiInterface;
    NetworkInfo networkInfo;
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
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            fetchTracks();
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    Intent intent = new Intent(MainActivity.this, TrackInfo.class);
                    Bundle extras = new Bundle();
                    extras.putInt("id", trackList.get(position).getTrack().getTrackId());
                    intent.putExtras(extras);
                    startActivity(intent);

                }

                @Override
                public void onLongClick(View view, int position) {
                }
            }));
        } else
            Toast.makeText(MainActivity.this, "CHECK YOUR NETWORK CONNECTIVITY",
                    Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.searchAct:
                if (networkInfo != null && networkInfo.isConnected()) {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(MainActivity.this, "CHECK YOUR NETWORK CONNECTIVITY",
                            Toast.LENGTH_SHORT).show();
                return true;
            case R.id.top_artists:
                if (networkInfo != null && networkInfo.isConnected()) {
                    Intent intent2 = new Intent(MainActivity.this, TopArtistsActivity.class);
                    startActivity(intent2);
                } else
                    Toast.makeText(MainActivity.this, "CHECK YOUR NETWORK CONNECTIVITY",
                            Toast.LENGTH_SHORT).show();
                return true;
            case R.id.favourites:
                Intent intent2 = new Intent(MainActivity.this, FavouritesActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    public void fetchTracks()
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
                Log.e(TAG, t.toString());
            }
        });
    }
}
