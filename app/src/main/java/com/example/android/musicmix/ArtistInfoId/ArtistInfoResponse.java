package com.example.android.musicmix.ArtistInfoId;

import com.example.android.musicmix.TopArtists.MessageArtist;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistInfoResponse {

    @SerializedName("message")
    @Expose
    private MessageArtistInfo message;

    public MessageArtistInfo getMessage() {
        return message;
    }

    public void setMessage(MessageArtistInfo message) {
        this.message = message;
    }

}