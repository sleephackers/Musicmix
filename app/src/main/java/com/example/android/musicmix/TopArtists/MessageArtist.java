
package com.example.android.musicmix.TopArtists;

import com.example.android.musicmix.TopTracks.Header;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageArtist {

    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("body")
    @Expose
    private BodyArtist body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public BodyArtist getBody() {
        return body;
    }

    public void setBody(BodyArtist body) {
        this.body = body;
    }

}