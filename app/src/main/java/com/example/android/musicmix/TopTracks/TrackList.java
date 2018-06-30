package com.example.android.musicmix.TopTracks;

import com.example.android.musicmix.TopTracks.Track;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackList {

    @SerializedName("track")
    @Expose
    private Track track;

    /**
     * No args constructor for use in serialization
     *
     */
    public TrackList() {
    }

    /**
     *
     * @param track
     */
    public TrackList(Track track) {
        super();
        this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

}


