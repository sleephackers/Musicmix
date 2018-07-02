package com.example.android.musicmix.SongArtist;

import com.example.android.musicmix.TopTracks.Body;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageSearchArtist {

    @SerializedName("header")
    @Expose
    private HeaderSearchArtist header;
    @SerializedName("body")
    @Expose
    private Body body;

    public HeaderSearchArtist getHeader() {
        return header;
    }

    public void setHeader(HeaderSearchArtist header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}