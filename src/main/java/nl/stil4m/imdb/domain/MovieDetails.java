package nl.stil4m.imdb.domain;

import java.util.List;

@lombok.Setter(lombok.AccessLevel.NONE)
@lombok.Data
public class MovieDetails {

    private final String movieName;
    private final Integer year;
    private final String description;
    private final Double rating;
    private final List<String> directors;
    private final List<String> writers;
    private final List<String> stars;
    private final List<String> categories;
    private final String image;

}
