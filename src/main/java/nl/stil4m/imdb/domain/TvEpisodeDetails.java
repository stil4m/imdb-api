package nl.stil4m.imdb.domain;

import java.util.List;

@lombok.Setter(lombok.AccessLevel.NONE)
@lombok.Data
public class TvEpisodeDetails {

    private final String showName;

    private final String episodeName;

    private final Long seasonNumber;

    private final Long episodeNumber;

    private final List<String> genres;

    private final Long airDate;

}
