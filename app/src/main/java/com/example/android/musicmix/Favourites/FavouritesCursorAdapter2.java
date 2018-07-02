package com.example.android.musicmix.Favourites;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicmix.R;
import com.example.android.musicmix.data.ArtistContract;

public class FavouritesCursorAdapter2 extends CursorAdapter {


    public FavouritesCursorAdapter2(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name1TextView = (TextView) view.findViewById(R.id.title_track);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_track);
        imageView.setVisibility(View.INVISIBLE);
        int nameColumnIndex = cursor.getColumnIndex(ArtistContract.ArtistEntry.COLUMN_NAME);

        String title = cursor.getString(nameColumnIndex);

        name1TextView.setText(title);
    }
}

