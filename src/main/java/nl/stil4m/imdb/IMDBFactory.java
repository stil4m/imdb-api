package nl.stil4m.imdb;

import nl.stil4m.imdb.parsers.MovieDetailsPageParser;
import nl.stil4m.imdb.parsers.MovieFindResultParser;
import nl.stil4m.imdb.parsers.SearchedMoviesParser;
import nl.stil4m.imdb.parsers.TvEpisodeDetailsPageParser;
import nl.stil4m.imdb.parsers.TvShowDetailsPageParser;
import nl.stil4m.imdb.util.ElementUtil;
import nl.stil4m.imdb.util.impl.DefaultElementUtil;

import java.util.Properties;

public class IMDBFactory {

    public IMDB createInstance(Properties properties) {
        ElementUtil elementUtil = new DefaultElementUtil();
        MovieFindResultParser movieFindResultParser = new MovieFindResultParser();
        SearchedMoviesParser searchedMoviesParser = new SearchedMoviesParser(movieFindResultParser);
        MovieDetailsPageParser movieDetailsPageParser = new MovieDetailsPageParser(elementUtil, properties);
        TvEpisodeDetailsPageParser tvEpisodeDetailsPageParser = new TvEpisodeDetailsPageParser(properties);
        TvShowDetailsPageParser tvShowDetailsPageParser = new TvShowDetailsPageParser(properties, elementUtil);
        return new IMDB(new DocumentBuilder(), searchedMoviesParser, movieDetailsPageParser, tvShowDetailsPageParser, tvEpisodeDetailsPageParser);
    }

}
