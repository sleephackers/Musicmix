package com.example.android.musicmix;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.musicmix.TopTracks.TrackList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<TrackList> trackLists;
    private Context context;

    public RecyclerAdapter(List<TrackList> trackLists, Context context) {
        this.trackLists = trackLists;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(trackLists.get(position).getTrack().getTrackName());
        Picasso.get()
                .load(trackLists.get(position).getTrack().getAlbumCoverart100x100())
                .resize(300, 300)
                .into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return trackLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_track);
            poster = view.findViewById(R.id.image_track);
        }
    }
}
