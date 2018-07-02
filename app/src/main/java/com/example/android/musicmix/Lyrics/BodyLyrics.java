package com.example.android.musicmix.Lyrics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BodyLyrics {

    @SerializedName("lyrics")
    @Expose
    private Lyric lyrics;

    public Lyric getLyrics() {
        return lyrics;
    }

    public void setLyrics(Lyric lyrics) {
        this.lyrics = lyrics;
    }

}