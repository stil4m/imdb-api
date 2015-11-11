package nl.stil4m.imdb;

import nl.stil4m.imdb.commands.SearchTitleCommand;
import nl.stil4m.imdb.commands.TitleDetailsCommand;
import nl.stil4m.imdb.domain.MovieDetails;
import nl.stil4m.imdb.domain.SearchResult;
import nl.stil4m.imdb.domain.TvEpisodeDetails;
import nl.stil4m.imdb.domain.TvShowDetails;
import nl.stil4m.imdb.exceptions.IMDBException;
import nl.stil4m.imdb.exceptions.MovieDetailsException;
import nl.stil4m.imdb.exceptions.ParseException;
import nl.stil4m.imdb.exceptions.TvEpisodeDetailsException;
import nl.stil4m.imdb.exceptions.TvShowDetailsException;
import nl.stil4m.imdb.filter.Predicate;
import nl.stil4m.imdb.parsers.MovieDetailsPageParser;
import nl.stil4m.imdb.parsers.SearchedMoviesParser;
import nl.stil4m.imdb.parsers.TvEpisodeDetailsPageParser;
import nl.stil4m.imdb.parsers.TvShowDetailsPageParser;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IMDB {

    private final DocumentBuilder documentBuilder;
    private final SearchedMoviesParser searchedMoviesParser;
    private final MovieDetailsPageParser movieDetailsPageParser;
    private final TvShowDetailsPageParser tvShowDetailsPageParser;
    private final TvEpisodeDetailsPageParser tvEpisodeDetailsPageParser;

    public IMDB(DocumentBuilder documentBuilder, SearchedMoviesParser searchedMoviesParser, MovieDetailsPageParser movieDetailsPageParser, TvShowDetailsPageParser tvShowDetailsPageParser, TvEpisodeDetailsPageParser tvEpisodeDetailsPageParser) {
        this.documentBuilder = documentBuilder;
        this.searchedMoviesParser = searchedMoviesParser;
        this.movieDetailsPageParser = movieDetailsPageParser;
        this.tvShowDetailsPageParser = tvShowDetailsPageParser;
        this.tvEpisodeDetailsPageParser = tvEpisodeDetailsPageParser;
    }

    public List<SearchResult> search(String query) throws IMDBException {
        try {
            Document doc = documentBuilder.buildDocument(new SearchTitleCommand(query));
            return searchedMoviesParser.parse(doc);
        } catch (IOException e) {
            throw new IMDBException("Could not find movies for name '" + query + "'", e);
        } catch (ParseException e) {
            throw new IMDBException("A parse exception occurred while searching movies for name '" + query + "'", e);
        }
    }

    public List<SearchResult> search(String movieName, final Predicate<SearchResult> searchResultFilter) throws IMDBException {
        List<SearchResult> searchResults = search(movieName);
        List<SearchResult> answer = new ArrayList<SearchResult>();
        for (SearchResult searchResult : searchResults) {
            if (searchResultFilter.accepts(searchResult)) {
                answer.add(searchResult);
            }
        }
        return answer;
    }

    public MovieDetails getMovieDetails(String movieId) throws IMDBException {
        try {
            Document doc = documentBuilder.buildDocument(new TitleDetailsCommand(movieId));
            return movieDetailsPageParser.parse(doc);
        } catch (Exception e) {
            throw new MovieDetailsException("Could not find movie details for id: '" + movieId + "'", e);
        }
    }

    public TvEpisodeDetails getTvEpisodeDetails(String episodeId) throws IMDBException {
        try {
            Document doc = documentBuilder.buildDocument(new TitleDetailsCommand(episodeId));
            return tvEpisodeDetailsPageParser.parse(doc);
        } catch (Exception e) {
            throw new TvEpisodeDetailsException("Could not find episode details for id: '" + episodeId + "'", e);
        }
    }

    public TvShowDetails getTvShowDetails(String showId) throws IMDBException {
        try {
            Document doc = documentBuilder.buildDocument(new TitleDetailsCommand(showId));
            return tvShowDetailsPageParser.parse(doc);
        } catch (Exception e) {
            throw new TvShowDetailsException("Could not find show details for id: '" + showId + "'", e);
        }
    }
}