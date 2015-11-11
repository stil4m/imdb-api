package nl.stil4m.imdb.domain;

@lombok.experimental.Builder
@lombok.AllArgsConstructor
@lombok.Data
public class SearchResult {

    private final String id;
    private final String name;
    private final int year;
    private final String type;

}
