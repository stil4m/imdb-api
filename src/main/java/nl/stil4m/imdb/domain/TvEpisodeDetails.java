package nl.stil4m.imdb.domain;

import java.util.List;

public class TvEpisodeDetails {

    private final String showName;

    private final String episodeName;

    private final Long seasonNumber;

    private final Long episodeNumber;

    private final List<String> genres;

    private final Long airDate;

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

    public String getEpisodeName() {
        return episodeName;
    }

    public Long getSeasonNumber() {
        return seasonNumber;
    }

    public Long getEpisodeNumber() {
        return episodeNumber;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Long getAirDate() {
        return airDate;
    }
}
