package com.example.android.musicmix.TrackInfoId;

import com.example.android.musicmix.TopTracks.Header;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessageTrackInfo {

    @SerializedName("header")
    @Expose
    private Header header;
    @SerializedName("body")
    @Expose
    private BodyTrackInfo body;

    /**
     * No args constructor for use in serialization
     */
    public MessageTrackInfo() {
    }

    /**
     * @param body
     * @param header
     */
    public MessageTrackInfo(Header header, BodyTrackInfo body) {
        super();
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public BodyTrackInfo getBody() {
        return body;
    }

    public void setBody(BodyTrackInfo body) {
        this.body = body;
    }

}