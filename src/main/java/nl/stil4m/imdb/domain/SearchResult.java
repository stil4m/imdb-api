package nl.stil4m.imdb.domain;

// TODO Make setters private
@lombok.Data
public class SearchResult {

    private String id;

    private String name;

    private Integer year;

    private String type;

    public SearchResult(String id, String name, Integer year, String type) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.type = type;
    }


}
