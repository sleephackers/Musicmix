package com.example.android.musicmix.TrackInfoId;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrackInfoResponse {

    @SerializedName("message")
    @Expose
    private MessageTrackInfo message;

    /**
     * No args constructor for use in serialization
     */
    public TrackInfoResponse() {
    }

    /**
     * @param message
     */
    public TrackInfoResponse(MessageTrackInfo message) {
        super();
        this.message = message;
    }

    public MessageTrackInfo getMessage() {
        return message;
    }

    public void setMessage(MessageTrackInfo message) {
        this.message = message;
    }

}