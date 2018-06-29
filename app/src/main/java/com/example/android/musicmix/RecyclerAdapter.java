package com.example.android.musicmix;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.MyViewHolder> {

    private List<Track> tracks;
    private Context context;

    public RecyclerAdapter(List<Track> tracks,Context context)
    {
        this.tracks=tracks;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.track_title.setText(tracks.get(position).getTrackName());
        Glide.with(context).load(tracks.get(position).getAlbumCoverart100x100()).into(holder.track_img);
    }

    @Override
    public int getItemCount() {
      return tracks.size();


    }
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView track_img;
        TextView track_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            track_img=(ImageView) itemView.findViewById(R.id.image_track);
            track_title=(TextView) itemView.findViewById(R.id.title_track);
        }
    }
}
