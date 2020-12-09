package nl.stil4m.imdb.domain;

import lombok.Data;
import lombok.Setter;

import java.util.List;

@Setter(lombok.AccessLevel.NONE)
@Data
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
    private final Integer duration;

}
