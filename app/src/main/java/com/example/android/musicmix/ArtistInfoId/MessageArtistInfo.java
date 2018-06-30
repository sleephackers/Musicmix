package com.example.android.musicmix.ArtistInfoId;

import com.example.android.musicmix.TopTracks.Header;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageArtistInfo {

    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("body")
    @Expose
    private BodyArtistInfo body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public BodyArtistInfo getBody() {
        return body;
    }

    public void setBody(BodyArtistInfo body) {
        this.body = body;
    }

}