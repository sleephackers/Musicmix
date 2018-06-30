package com.example.android.musicmix.ArtistInfoId;

import com.example.android.musicmix.TopArtists.Artist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyArtistInfo {

    @SerializedName("artist")
    @Expose
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}