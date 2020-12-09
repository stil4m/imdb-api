package nl.stil4m.imdb.domain;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter(lombok.AccessLevel.NONE)
@Data
public class TvEpisodeDetails {

    private final String showName;
    private final String episodeName;
    private final long seasonNumber;
    private final long episodeNumber;
    private final List<String> genres;
    private final LocalDate airDate;

}
