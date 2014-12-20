package nl.stil4m.imdb.domain;

import java.util.List;

@lombok.Setter(lombok.AccessLevel.NONE)
@lombok.Data
public class TvShowDetails {

    private final String name;
    private final Double rating;
    private final Integer startYear;
    private final Integer endYear;
    private final Integer duration;
    private final List<String> genres;
    private final String plot;
    private final List<String> creators;

}
