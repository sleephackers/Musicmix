package com.example.android.musicmix;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Header {


    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("execute_time")
    @Expose
    private Double executeTime;

    /**
     * No args constructor for use in serialization
     *
     */
    public Header() {
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Double getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Double executeTime) {
        this.executeTime = executeTime;
    }

}