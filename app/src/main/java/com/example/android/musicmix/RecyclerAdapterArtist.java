package com.example.android.musicmix;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicmix.TopArtists.ArtistList;


import java.util.List;

public class RecyclerAdapterArtist extends RecyclerView.Adapter<RecyclerAdapterArtist.MyViewHolder> {

    private List<ArtistList> artistList;
    private Context context;

    public RecyclerAdapterArtist(List<ArtistList> artistLists, Context context) {
        this.artistList = artistLists;
        this.context = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(artistList.get(position).getArtist().getArtistName());

    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_track);
            poster = view.findViewById(R.id.image_track);
            poster.setVisibility(View.GONE);
        }
    }
}
