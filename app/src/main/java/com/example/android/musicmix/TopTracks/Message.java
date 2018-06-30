package com.example.android.musicmix.TopTracks;

import com.example.android.musicmix.TopTracks.Body;
import com.example.android.musicmix.TopTracks.Header;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {


    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("body")
    @Expose
    private Body body;

    /**
     * No args constructor for use in serialization
     *
     */
    public Message() {
    }


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}