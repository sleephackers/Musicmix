package com.example.android.musicmix.Lyrics;

import com.example.android.musicmix.TopTracks.Header;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageLyric {

    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("body")
    @Expose
    private BodyLyrics body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public BodyLyrics getBody() {
        return body;
    }

    public void setBody(BodyLyrics body) {
        this.body = body;
    }

}