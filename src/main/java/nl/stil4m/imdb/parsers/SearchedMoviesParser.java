package nl.stil4m.imdb.parsers;

import nl.stil4m.imdb.domain.SearchResult;
import nl.stil4m.imdb.exceptions.ParseException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class SearchedMoviesParser implements Parser<List<SearchResult>> {

    private MovieFindResultParser movieFindResultParser;

    public SearchedMoviesParser(MovieFindResultParser movieFindResultParser) {
        this.movieFindResultParser = movieFindResultParser;
    }

    @Override
    public List<SearchResult> parse(Element document) throws ParseException {
        List<SearchResult> searchResultList = new ArrayList<SearchResult>();

        Element element = getSearchResultsFromDocument(document);
        if (element == null) {
            return searchResultList;
        }

        Elements results = getSearchResults(element);
        try {
            for (Element result : results) {
                SearchResult searchResult = movieFindResultParser.parse(result);
                searchResultList.add(searchResult);
            }
        } catch (ParseException e) {
            throw new ParseException("Could not parse search results", e);
        }
        return searchResultList;
    }

    private Elements getSearchResults(Element element) {
        return element.select(".findList .findResult");
    }

    private Element getSearchResultsFromDocument(Element document) {
        Elements findSections = document.select("#main .article .findSection");

        if (findSections.size() != 1) {
            //This happens when there are no search results
            return null;
        }
        return findSections.get(0);
    }

}