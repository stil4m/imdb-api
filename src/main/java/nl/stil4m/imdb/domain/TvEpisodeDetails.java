package nl.stil4m.imdb.domain;

import java.util.List;

@lombok.Setter(lombok.AccessLevel.NONE)
@lombok.Data
public class TvEpisodeDetails {

    private final String showName;
    private final String episodeName;
    private final long seasonNumber;
    private final long episodeNumber;
    private final List<String> genres;
    private final long airDate;

}
