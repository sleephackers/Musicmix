package com.example.android.musicmix;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("track_id")
    @Expose
    private Integer trackId;
    @SerializedName("track_mbid")
    @Expose
    private String trackMbid;
    @SerializedName("track_isrc")
    @Expose
    private String trackIsrc;
    @SerializedName("track_spotify_id")
    @Expose
    private String trackSpotifyId;
    @SerializedName("track_soundcloud_id")
    @Expose
    private String trackSoundcloudId;
    @SerializedName("track_xboxmusic_id")
    @Expose
    private String trackXboxmusicId;
    @SerializedName("track_name")
    @Expose
    private String trackName;
    @SerializedName("track_name_translation_list")
    @Expose
    private List<Object> trackNameTranslationList = null;
    @SerializedName("track_rating")
    @Expose
    private Integer trackRating;
    @SerializedName("track_length")
    @Expose
    private Integer trackLength;
    @SerializedName("commontrack_id")
    @Expose
    private Integer commontrackId;
    @SerializedName("instrumental")
    @Expose
    private Integer instrumental;
    @SerializedName("explicit")
    @Expose
    private Integer explicit;
    @SerializedName("has_lyrics")
    @Expose
    private Integer hasLyrics;
    @SerializedName("has_lyrics_crowd")
    @Expose
    private Integer hasLyricsCrowd;
    @SerializedName("has_subtitles")
    @Expose
    private Integer hasSubtitles;
    @SerializedName("has_richsync")
    @Expose
    private Integer hasRichsync;
    @SerializedName("num_favourite")
    @Expose
    private Integer numFavourite;
    @SerializedName("lyrics_id")
    @Expose
    private Integer lyricsId;
    @SerializedName("subtitle_id")
    @Expose
    private Integer subtitleId;
    @SerializedName("album_id")
    @Expose
    private Integer albumId;
    @SerializedName("album_name")
    @Expose
    private String albumName;
    @SerializedName("artist_id")
    @Expose
    private Integer artistId;
    @SerializedName("artist_mbid")
    @Expose
    private String artistMbid;
    @SerializedName("artist_name")
    @Expose
    private String artistName;
    @SerializedName("album_coverart_100x100")
    @Expose
    private String albumCoverart100x100;
    @SerializedName("album_coverart_350x350")
    @Expose
    private String albumCoverart350x350;
    @SerializedName("album_coverart_500x500")
    @Expose
    private String albumCoverart500x500;
    @SerializedName("album_coverart_800x800")
    @Expose
    private String albumCoverart800x800;
    @SerializedName("track_share_url")
    @Expose
    private String trackShareUrl;
    @SerializedName("track_edit_url")
    @Expose
    private String trackEditUrl;
    @SerializedName("commontrack_vanity_id")
    @Expose
    private String commontrackVanityId;
    @SerializedName("restricted")
    @Expose
    private Integer restricted;
    @SerializedName("first_release_date")
    @Expose
    private String firstReleaseDate;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;
    @SerializedName("primary_genres")
    @Expose
    private PrimaryGenres primaryGenres;
    @SerializedName("secondary_genres")
    @Expose
    private SecondaryGenres secondaryGenres;

    /**
     * No args constructor for use in serialization
     *
     */
    public Track() {
    }

    /**
     *
     * @param firstReleaseDate
     * @param hasLyrics
     * @param trackXboxmusicId
     * @param albumCoverart500x500
     * @param artistMbid
     * @param trackName
     * @param secondaryGenres
     * @param lyricsId
     * @param artistId
     * @param trackSpotifyId
     * @param trackEditUrl
     * @param albumCoverart800x800
     * @param updatedTime
     * @param albumCoverart350x350
     * @param trackNameTranslationList
     * @param albumCoverart100x100
     * @param trackId
     * @param albumName
     * @param albumId
     * @param restricted
     * @param trackMbid
     * @param trackSoundcloudId
     * @param hasRichsync
     * @param trackRating
     * @param hasLyricsCrowd
     * @param subtitleId
     * @param trackIsrc
     * @param hasSubtitles
     * @param explicit
     * @param instrumental
     * @param primaryGenres
     * @param commontrackVanityId
     * @param artistName
     * @param numFavourite
     * @param trackShareUrl
     * @param commontrackId
     * @param trackLength
     */
    public Track(Integer trackId, String trackMbid, String trackIsrc, String trackSpotifyId, String trackSoundcloudId, String trackXboxmusicId, String trackName, List<Object> trackNameTranslationList, Integer trackRating, Integer trackLength, Integer commontrackId, Integer instrumental, Integer explicit, Integer hasLyrics, Integer hasLyricsCrowd, Integer hasSubtitles, Integer hasRichsync, Integer numFavourite, Integer lyricsId, Integer subtitleId, Integer albumId, String albumName, Integer artistId, String artistMbid, String artistName, String albumCoverart100x100, String albumCoverart350x350, String albumCoverart500x500, String albumCoverart800x800, String trackShareUrl, String trackEditUrl, String commontrackVanityId, Integer restricted, String firstReleaseDate, String updatedTime, PrimaryGenres primaryGenres, SecondaryGenres secondaryGenres) {
        super();
        this.trackId = trackId;
        this.trackMbid = trackMbid;
        this.trackIsrc = trackIsrc;
        this.trackSpotifyId = trackSpotifyId;
        this.trackSoundcloudId = trackSoundcloudId;
        this.trackXboxmusicId = trackXboxmusicId;
        this.trackName = trackName;
        this.trackNameTranslationList = trackNameTranslationList;
        this.trackRating = trackRating;
        this.trackLength = trackLength;
        this.commontrackId = commontrackId;
        this.instrumental = instrumental;
        this.explicit = explicit;
        this.hasLyrics = hasLyrics;
        this.hasLyricsCrowd = hasLyricsCrowd;
        this.hasSubtitles = hasSubtitles;
        this.hasRichsync = hasRichsync;
        this.numFavourite = numFavourite;
        this.lyricsId = lyricsId;
        this.subtitleId = subtitleId;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artistId = artistId;
        this.artistMbid = artistMbid;
        this.artistName = artistName;
        this.albumCoverart100x100 = albumCoverart100x100;
        this.albumCoverart350x350 = albumCoverart350x350;
        this.albumCoverart500x500 = albumCoverart500x500;
        this.albumCoverart800x800 = albumCoverart800x800;
        this.trackShareUrl = trackShareUrl;
        this.trackEditUrl = trackEditUrl;
        this.commontrackVanityId = commontrackVanityId;
        this.restricted = restricted;
        this.firstReleaseDate = firstReleaseDate;
        this.updatedTime = updatedTime;
        this.primaryGenres = primaryGenres;
        this.secondaryGenres = secondaryGenres;
    }

    public Integer getTrackId() {
        return trackId;
    }

    public void setTrackId(Integer trackId) {
        this.trackId = trackId;
    }

    public String getTrackMbid() {
        return trackMbid;
    }

    public void setTrackMbid(String trackMbid) {
        this.trackMbid = trackMbid;
    }

    public String getTrackIsrc() {
        return trackIsrc;
    }

    public void setTrackIsrc(String trackIsrc) {
        this.trackIsrc = trackIsrc;
    }

    public String getTrackSpotifyId() {
        return trackSpotifyId;
    }

    public void setTrackSpotifyId(String trackSpotifyId) {
        this.trackSpotifyId = trackSpotifyId;
    }

    public String getTrackSoundcloudId() {
        return trackSoundcloudId;
    }

    public void setTrackSoundcloudId(String trackSoundcloudId) {
        this.trackSoundcloudId = trackSoundcloudId;
    }

    public String getTrackXboxmusicId() {
        return trackXboxmusicId;
    }

    public void setTrackXboxmusicId(String trackXboxmusicId) {
        this.trackXboxmusicId = trackXboxmusicId;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public List<Object> getTrackNameTranslationList() {
        return trackNameTranslationList;
    }

    public void setTrackNameTranslationList(List<Object> trackNameTranslationList) {
        this.trackNameTranslationList = trackNameTranslationList;
    }

    public Integer getTrackRating() {
        return trackRating;
    }

    public void setTrackRating(Integer trackRating) {
        this.trackRating = trackRating;
    }

    public Integer getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Integer trackLength) {
        this.trackLength = trackLength;
    }

    public Integer getCommontrackId() {
        return commontrackId;
    }

    public void setCommontrackId(Integer commontrackId) {
        this.commontrackId = commontrackId;
    }

    public Integer getInstrumental() {
        return instrumental;
    }

    public void setInstrumental(Integer instrumental) {
        this.instrumental = instrumental;
    }

    public Integer getExplicit() {
        return explicit;
    }

    public void setExplicit(Integer explicit) {
        this.explicit = explicit;
    }

    public Integer getHasLyrics() {
        return hasLyrics;
    }

    public void setHasLyrics(Integer hasLyrics) {
        this.hasLyrics = hasLyrics;
    }

    public Integer getHasLyricsCrowd() {
        return hasLyricsCrowd;
    }

    public void setHasLyricsCrowd(Integer hasLyricsCrowd) {
        this.hasLyricsCrowd = hasLyricsCrowd;
    }

    public Integer getHasSubtitles() {
        return hasSubtitles;
    }

    public void setHasSubtitles(Integer hasSubtitles) {
        this.hasSubtitles = hasSubtitles;
    }

    public Integer getHasRichsync() {
        return hasRichsync;
    }

    public void setHasRichsync(Integer hasRichsync) {
        this.hasRichsync = hasRichsync;
    }

    public Integer getNumFavourite() {
        return numFavourite;
    }

    public void setNumFavourite(Integer numFavourite) {
        this.numFavourite = numFavourite;
    }

    public Integer getLyricsId() {
        return lyricsId;
    }

    public void setLyricsId(Integer lyricsId) {
        this.lyricsId = lyricsId;
    }

    public Integer getSubtitleId() {
        return subtitleId;
    }

    public void setSubtitleId(Integer subtitleId) {
        this.subtitleId = subtitleId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtistMbid() {
        return artistMbid;
    }

    public void setArtistMbid(String artistMbid) {
        this.artistMbid = artistMbid;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumCoverart100x100() {
        return albumCoverart100x100;
    }

    public void setAlbumCoverart100x100(String albumCoverart100x100) {
        this.albumCoverart100x100 = albumCoverart100x100;
    }

    public String getAlbumCoverart350x350() {
        return albumCoverart350x350;
    }

    public void setAlbumCoverart350x350(String albumCoverart350x350) {
        this.albumCoverart350x350 = albumCoverart350x350;
    }

    public String getAlbumCoverart500x500() {
        return albumCoverart500x500;
    }

    public void setAlbumCoverart500x500(String albumCoverart500x500) {
        this.albumCoverart500x500 = albumCoverart500x500;
    }

    public String getAlbumCoverart800x800() {
        return albumCoverart800x800;
    }

    public void setAlbumCoverart800x800(String albumCoverart800x800) {
        this.albumCoverart800x800 = albumCoverart800x800;
    }

    public String getTrackShareUrl() {
        return trackShareUrl;
    }

    public void setTrackShareUrl(String trackShareUrl) {
        this.trackShareUrl = trackShareUrl;
    }

    public String getTrackEditUrl() {
        return trackEditUrl;
    }

    public void setTrackEditUrl(String trackEditUrl) {
        this.trackEditUrl = trackEditUrl;
    }

    public String getCommontrackVanityId() {
        return commontrackVanityId;
    }

    public void setCommontrackVanityId(String commontrackVanityId) {
        this.commontrackVanityId = commontrackVanityId;
    }

    public Integer getRestricted() {
        return restricted;
    }

    public void setRestricted(Integer restricted) {
        this.restricted = restricted;
    }

    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public PrimaryGenres getPrimaryGenres() {
        return primaryGenres;
    }

    public void setPrimaryGenres(PrimaryGenres primaryGenres) {
        this.primaryGenres = primaryGenres;
    }

    public SecondaryGenres getSecondaryGenres() {
        return secondaryGenres;
    }

    public void setSecondaryGenres(SecondaryGenres secondaryGenres) {
        this.secondaryGenres = secondaryGenres;
    }

}
