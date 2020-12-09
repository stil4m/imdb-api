package nl.stil4m.imdb.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@Data
public class SearchResult {

    private final String id;
    private final String name;
    private final int year;
    private final String type;
    private final String thumbnail;

}
