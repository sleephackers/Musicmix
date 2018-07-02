package com.example.android.musicmix.Favourites;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicmix.R;
import com.example.android.musicmix.data.TrackContract;

public class FavouriteTrackInfo extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_TRACK_LOADER = 0;
    private Uri mCurrentUri;
    private TextView title;
    private TextView artist;
    private TextView album;
    private TextView genre;
    private TextView yearofrelease;
    private Button lyricsclick;
    private ImageView poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_info);
        Intent intent = getIntent();
        mCurrentUri = intent.getData();

        title = findViewById(R.id.title);
        artist = findViewById(R.id.artist);
        album = findViewById(R.id.album);
        genre = findViewById(R.id.genres);
        yearofrelease = findViewById(R.id.yearofrelease);
        lyricsclick = findViewById(R.id.lyrics);
        poster = findViewById(R.id.poster);
        poster.setVisibility(View.INVISIBLE);
        getLoaderManager().initLoader(EXISTING_TRACK_LOADER, null, this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favourite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                TrackContract.TrackEntry._ID,
                TrackContract.TrackEntry.COLUMN_TITLE,
                TrackContract.TrackEntry.COLUMN_ARTIST,
                TrackContract.TrackEntry.COLUMN_ALBUM,
                TrackContract.TrackEntry.COLUMN_GENRE,
                TrackContract.TrackEntry.COLUMN_YEAROFRELEASE,
                TrackContract.TrackEntry.COLUMN_LYRICS
        };

        return new CursorLoader(this,   // Parent activity context
                mCurrentUri,         // Query the content URI for the current track
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }


        if (cursor.moveToFirst()) {
            int titleci = cursor.getColumnIndex(TrackContract.TrackEntry.COLUMN_TITLE);
            int artistci = cursor.getColumnIndex(TrackContract.TrackEntry.COLUMN_ARTIST);
            int albumci = cursor.getColumnIndex(TrackContract.TrackEntry.COLUMN_ALBUM);
            int genreci = cursor.getColumnIndex(TrackContract.TrackEntry.COLUMN_GENRE);
            int yearofreleaseci = cursor.getColumnIndex(TrackContract.TrackEntry.COLUMN_YEAROFRELEASE);
            int lyricsci = cursor.getColumnIndex(TrackContract.TrackEntry.COLUMN_LYRICS);

            // Extract out the value from the Cursor for the given column index
            String titles = cursor.getString(titleci);
            String artists = cursor.getString(artistci);
            String albums = cursor.getString(albumci);
            String genres = cursor.getString(genreci);
            String yearofreleases = cursor.getString(yearofreleaseci);
            final String lyricss = cursor.getString(lyricsci);

            // Update the views on the screen with the values from the database
            title.setText(titles);
            artist.setText(artists);
            album.setText(albums);
            genre.setText(genres);
            yearofrelease.setText(yearofreleases);
            lyricsclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FavouriteTrackInfo.this, FavouriteLyrics.class);
                    Bundle extras = new Bundle();
                    extras.putString("lyrics", lyricss);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        title.setText("");
        artist.setText("");
        album.setText("");
        genre.setText("");
        yearofrelease.setText("");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this Track");
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteTrack();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void deleteTrack() {
        if (mCurrentUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, "Error with deleting Track",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Track deleted",
                        Toast.LENGTH_SHORT).show();
            }
            finish();

        }
    }
}