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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.musicmix.R;
import com.example.android.musicmix.data.ArtistContract;

public class FavouriteArtistInfo extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_ARTIST_LOADER = 0;
    private Uri mCurrentUri;
    private TextView name;
    private TextView country;
    private TextView genre;
    private TextView rating;
    private ImageView poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_info);
        Intent intent = getIntent();
        mCurrentUri = intent.getData();

        name = findViewById(R.id.name);
        country = findViewById(R.id.country);
        genre = findViewById(R.id.genre);
        rating = findViewById(R.id.rating);
        getLoaderManager().initLoader(EXISTING_ARTIST_LOADER, null, this);


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
                ArtistContract.ArtistEntry._ID,
                ArtistContract.ArtistEntry.COLUMN_NAME,
                ArtistContract.ArtistEntry.COLUMN_COUNTRY,
                ArtistContract.ArtistEntry.COLUMN_GENRE,
                ArtistContract.ArtistEntry.COLUMN_RATING
        };

        return new CursorLoader(this,   // Parent activity context
                mCurrentUri,         // Query the content URI for the current artist
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
            int nameci = cursor.getColumnIndex(ArtistContract.ArtistEntry.COLUMN_NAME);
            int countryci = cursor.getColumnIndex(ArtistContract.ArtistEntry.COLUMN_COUNTRY);
            int genreci = cursor.getColumnIndex(ArtistContract.ArtistEntry.COLUMN_GENRE);
            int ratingci = cursor.getColumnIndex(ArtistContract.ArtistEntry.COLUMN_RATING);

            // Extract out the value from the Cursor for the given column index
            String names = cursor.getString(nameci);
            String countrys = cursor.getString(countryci);
            String genres = cursor.getString(genreci);
            String ratings = cursor.getString(ratingci);

            // Update the views on the screen with the values from the database
            name.setText(names);
            country.setText(countrys);
            genre.setText(genres);
            rating.setText(ratings);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        name.setText("");
        country.setText("");
        genre.setText("");
        rating.setText("");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this Artist");
        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteArtist();
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


    private void deleteArtist() {
        if (mCurrentUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, "Error with deleting Artist",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Artist deleted",
                        Toast.LENGTH_SHORT).show();
            }
            finish();

        }
    }
}