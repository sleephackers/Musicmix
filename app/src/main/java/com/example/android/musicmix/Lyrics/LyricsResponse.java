package com.example.android.musicmix.Lyrics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LyricsResponse {

    @SerializedName("message")
    @Expose
    private MessageLyric message;

    public MessageLyric getMessage() {
        return message;
    }

    public void setMessage(MessageLyric message) {
        this.message = message;
    }

}