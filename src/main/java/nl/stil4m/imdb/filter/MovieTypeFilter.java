package nl.stil4m.imdb.filter;

import nl.stil4m.imdb.domain.SearchResult;

public class MovieTypeFilter implements Predicate<SearchResult> {

    @Override
    public boolean accepts(SearchResult searchResult) {
        return searchResult.getType() != null && (
                "movie".equals(searchResult.getType().toLowerCase()) ||
                        "short".equals(searchResult.getType().toLowerCase())
        );
    }

}
