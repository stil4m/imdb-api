package nl.stil4m.imdb.domain;

public class SearchResult {

    private final String id;

    private final String name;

    private Integer year;

    private String type;

    public SearchResult(String id, String name, Integer year, String type) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Integer getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return id + " / " + name + " / " + year + " / " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SearchResult)) {
            return false;
        }
        SearchResult other = (SearchResult) o;
        return other.getId().equals(id) &&
                other.getName().equals(name) &&
                other.getYear().equals(year) &&
                other.getType().equals(type);
    }

}
