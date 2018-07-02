package com.example.android.musicmix.SongArtist;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchArtist {

    @SerializedName("message")
    @Expose
    private MessageSearchArtist message;

    public MessageSearchArtist getMessage() {
        return message;
    }

    public void setMessage(MessageSearchArtist message) {
        this.message = message;
    }

}