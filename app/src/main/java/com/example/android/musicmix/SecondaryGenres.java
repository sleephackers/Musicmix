package com.example.android.musicmix;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SecondaryGenres {

    @SerializedName("music_genre_list")
    @Expose
    private List<Object> musicGenreList = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public SecondaryGenres() {
    }

    /**
     *
     * @param musicGenreList
     */
    public SecondaryGenres(List<Object> musicGenreList) {
        super();
        this.musicGenreList = musicGenreList;
    }

    public List<Object> getMusicGenreList() {
        return musicGenreList;
    }

    public void setMusicGenreList(List<Object> musicGenreList) {
        this.musicGenreList = musicGenreList;
    }

}