package nl.stil4m.imdb.domain;

import java.util.List;

public class TvShowDetails {

    private String name;
    private Double rating;
    private Integer startYear;
    private Integer endYear;
    private Integer duration;
    private List<String> genres;
    private String plot;
    private List<String> creators;


    public TvShowDetails(String name, Double rating, Integer startYear, Integer endYear, Integer duration, List<String> genres, String plot, List<String> creators) {
        this.name = name;
        this.rating = rating;
        this.startYear = startYear;
        this.endYear = endYear;
        this.duration = duration;
        this.genres = genres;
        this.plot = plot;
        this.creators = creators;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public Integer getDuration() {
        return duration;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getPlot() {
        return plot;
    }

    public List<String> getCreators() {
        return creators;
    }
}
