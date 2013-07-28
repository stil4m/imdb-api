package nl.stil4m.imdb.domain;

import java.util.List;

public class TvEpisodeDetails {

    private String showName;

    private String episodeName;

    private Long seasonNumber;

    private Long episodeNumber;

    private List<String> genres;

    private Long airDate;

    public TvEpisodeDetails(String showName, String episodeName, Long seasonNumber, Long episodeNumber, List<String> genres, Long airDate) {
        this.showName = showName;
        this.episodeName = episodeName;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.genres = genres;
        this.airDate = airDate;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getEpisodeName() {
        return episodeName;
    }

    public void setEpisodeName(String episodeName) {
        this.episodeName = episodeName;
    }

    public Long getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Long seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Long getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Long episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Long getAirDate() {
        return airDate;
    }

    public void setAirDate(Long airDate) {
        this.airDate = airDate;
    }

}
