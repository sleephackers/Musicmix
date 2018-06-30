package com.example.android.musicmix.TopArtists;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistResponse {

    @SerializedName("message")
    @Expose
    private MessageArtist message;

    public MessageArtist getMessage() {
        return message;
    }

    public void setMessage(MessageArtist message) {
        this.message = message;
    }

}