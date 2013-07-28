package nl.stil4m.imdb.filter;

import com.google.common.base.Predicate;

import nl.stil4m.imdb.domain.SearchResult;

public interface SearchResultFilter extends Predicate<SearchResult> {

}
