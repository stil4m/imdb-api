package nl.stil4m.imdb.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Set;

@Setter(AccessLevel.NONE)
@Data
public class TvShowDetails {

    private final String name;
    private final double rating;
    private final int startYear;
    private final int endYear;
    private final int duration;
    private final Set<String> genres;
    private final String plot;
    private final Set<String> creators;

}
