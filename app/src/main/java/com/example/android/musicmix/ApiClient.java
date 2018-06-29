package com.example.android.musicmix;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL ="http://api.musixmatch.com/ws/1.1/";
    public static Retrofit retrofit=null;

    public static Retrofit getApiClient()
    {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
