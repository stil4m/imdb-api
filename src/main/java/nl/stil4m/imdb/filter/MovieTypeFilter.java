package nl.stil4m.imdb.filter;

import nl.stil4m.imdb.domain.SearchResult;

public class MovieTypeFilter implements SearchResultFilter {

    @Override
    public boolean apply(SearchResult searchResult) {
        return searchResult.getType() != null && (
                "movie".equals(searchResult.getType().toLowerCase()) ||
                "short".equals(searchResult.getType().toLowerCase())
        );
    }

}
