package nl.stil4m.imdb.domain;

import java.util.List;

@lombok.Setter(lombok.AccessLevel.NONE)
@lombok.Data
public class TvShowDetails {

    private final String name;
    private final double rating;
    private final int startYear;
    private final int endYear;
    private final int duration;
    private final List<String> genres;
    private final String plot;
    private final List<String> creators;

}
