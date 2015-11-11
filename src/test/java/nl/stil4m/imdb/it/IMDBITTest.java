package nl.stil4m.imdb.it;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import nl.stil4m.imdb.IMDB;
import nl.stil4m.imdb.IMDBFactory;
import nl.stil4m.imdb.domain.MovieDetails;
import nl.stil4m.imdb.domain.SearchResult;
import nl.stil4m.imdb.domain.TvEpisodeDetails;
import nl.stil4m.imdb.domain.TvShowDetails;
import nl.stil4m.imdb.exceptions.IMDBException;
import nl.stil4m.imdb.exceptions.MovieDetailsException;
import nl.stil4m.imdb.filter.MovieTypeFilter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Category(IntegrationTest.class)
public class IMDBITTest {

    private IMDB imdb;

    @Before
    public void before() {
        Properties properties = new Properties();

        try {
            InputStream inputStream = getClass().getResourceAsStream("/nl/stil4m/imdb/parsing.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            fail();
        }

        imdb = new IMDBFactory().createInstance(properties);
    }

    @Test
    public void testSearch() throws IMDBException {

        List<SearchResult> resultList = imdb.search("inglourious basterds");

        assertThat(resultList.size(), is(8));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterds", 2009, "Movie")), is(true));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterds", 2009, "Abc")), is(false));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterds", 2008, "Movie")), is(false));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterd", 2009, "Movie")), is(false));
        assertThat(resultList.contains(new SearchResult("tt1515156", "Inglourious Basterds: Movie Special", 2009, "TV Movie")), is(true));
        assertThat(resultList.contains(new SearchResult("tt0000000", "Nijntje", 0, "Abc")), is(false));
    }

    @Test
    public void testSearchWithFilter() throws IMDBException {

        List<SearchResult> resultList = imdb.search("inglourious basterds", new MovieTypeFilter());

        assertThat(resultList.size(), is(2));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterds", 2009, "Movie")), is(true));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterds", 2009, "Abc")), is(false));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterds", 2008, "Movie")), is(false));
        assertThat(resultList.contains(new SearchResult("tt0361748", "Inglourious Basterd", 2009, "Movie")), is(false));
        assertThat(resultList.contains(new SearchResult("tt0000000", "Nijntje", 0, "Abc")), is(false));
    }

    @Test
    public void testSearch2() throws IMDBException {

        imdb.search("inglourious basterds");
    }

    @Test
    public void testSearch3() throws IMDBException {

        imdb.search("drift");
    }

    @Test
    public void testSearch4() throws IMDBException {

        List<SearchResult> resultList = imdb.search("drift");
        assertThat(resultList.contains(new SearchResult("tt1714833", "Drift", 2013, "Movie")), is(true));
    }


    @Test
    public void testFetch() throws IMDBException {


        MovieDetails movieDetails = imdb.getMovieDetails("tt0361748");

        assertThat(movieDetails.getMovieName(), is("Inglourious Basterds"));
        assertThat(movieDetails.getYear(), is(2009));
        assertThat(movieDetails.getRating(), is(8.3));
        assertThat(movieDetails.getDescription(), is("In Nazi-occupied France during World War II, a plan to assassinate Nazi leaders by a group of Jewish U.S. soldiers coincides with a theatre owner's vengeful plans for the same."));
        assertThat(movieDetails.getDirectors(), is((List<String>) Lists.newArrayList("Quentin Tarantino", "Eli Roth")));
        assertThat(movieDetails.getWriters(), is((List<String>) Lists.newArrayList("Quentin Tarantino")));
        assertThat(movieDetails.getStars().contains("Brad Pitt"), is(true));
        assertThat(movieDetails.getCategories(), is((List<String>) Lists.newArrayList("Adventure", "Drama", "War")));
        assertThat(movieDetails.getImage(), is("http://ia.media-imdb.com/images/M/MV5BMjIzMDI4MTUzOV5BMl5BanBnXkFtZTcwNDY3NjA3Mg@@._V1_SY317_CR0,0,214,317_AL_.jpg"));
    }

    @Test
    public void testFetchAlternative() throws IMDBException {


        MovieDetails movieDetails = imdb.getMovieDetails("tt0477348");

        assertThat(movieDetails.getMovieName(), is("No Country for Old Men"));
        assertThat(movieDetails.getYear(), is(2007));
        assertThat(movieDetails.getRating(), is(8.1));
        assertThat(movieDetails.getDescription(), is("Violence and mayhem ensue after a hunter stumbles upon a drug deal gone wrong and more than two million dollars in cash near the Rio Grande."));
        assertThat(movieDetails.getDirectors(), is((List<String>) Lists.newArrayList("Ethan Coen", "Joel Coen")));
        assertThat(movieDetails.getWriters(), is((List<String>) Lists.newArrayList("Joel Coen", "Ethan Coen")));
        assertThat(movieDetails.getStars().size(), is(3));
        assertThat(movieDetails.getStars().contains("Tommy Lee Jones"), is(true));
        assertThat(movieDetails.getCategories(), is((List<String>) Lists.newArrayList("Crime", "Drama", "Thriller")));
    }

    @Test
    public void testFetchAlternative2() throws IMDBException {


        MovieDetails movieDetails = imdb.getMovieDetails("tt1392214");

        assertThat(movieDetails.getMovieName(), is("Prisoners"));
        assertThat(movieDetails.getYear(), is(2013));
        assertThat(movieDetails.getRating(), is(8.1));
        assertThat(movieDetails.getDescription(), is("When Keller Dover's daughter and her friend go missing, he takes matters into his own hands as the police pursue multiple leads and the pressure mounts. But just how far will this desperate father go to protect his family?"));
        assertThat(movieDetails.getDirectors(), is((List<String>) Lists.newArrayList("Denis Villeneuve")));
        assertThat(movieDetails.getWriters(), is((List<String>) Lists.newArrayList("Aaron Guzikowski")));

        assertThat(movieDetails.getStars().size(), is(3));
        assertThat(movieDetails.getStars().contains("Hugh Jackman"), is(true));
    }

    @Test
    public void testFetchAlternative3() throws IMDBException {


        MovieDetails movieDetails = imdb.getMovieDetails("tt2310332");
        assertThat(movieDetails.getMovieName(), is("The Hobbit: The Battle of the Five Armies"));
    }

    @Test
    public void testFetchAlternative4() throws IMDBException {

        imdb.getMovieDetails("tt1535438");
    }

    @Test
    public void testFetchAlternative4a() throws IMDBException {

        imdb.getMovieDetails("tt1615919");
    }

    @Test(expected = MovieDetailsException.class)
    public void testFetchAlternative5() throws IMDBException {

        imdb.getMovieDetails("tt1871715");
    }

    @Test
    public void testFetchTvEpisodeInformation() throws IMDBException {

        TvEpisodeDetails tvEpisodeDetails = imdb.getTvEpisodeDetails("tt1871715");
        assertThat(tvEpisodeDetails.getAirDate(), is(DateTime.parse("29 Sep. 2010", DateTimeFormat.forPattern("dd MMM. yyyy")).getMillis()));
        assertThat(tvEpisodeDetails.getSeasonNumber(), is(2L));
        assertThat(tvEpisodeDetails.getEpisodeNumber(), is(4L));
        assertThat(tvEpisodeDetails.getEpisodeName(), is("Chris Hölsken/Henk Bleker/Bryan Rookhuijzen"));
        assertThat(tvEpisodeDetails.getShowName(), is("Moraalridders"));
        assertThat(tvEpisodeDetails.getGenres(), is((List<String>) Lists.newArrayList("Talk-Show")));
    }

    @Test
    public void testFetchTvEpisodeInformation2() throws IMDBException {

        TvEpisodeDetails tvEpisodeDetails = imdb.getTvEpisodeDetails("tt2178802");
        assertThat(tvEpisodeDetails.getAirDate(), is(DateTime.parse("14 Apr. 2013", DateTimeFormat.forPattern("dd MMM. yyyy")).getMillis()));
        assertThat(tvEpisodeDetails.getSeasonNumber(), is(3L));
        assertThat(tvEpisodeDetails.getEpisodeNumber(), is(3L));
        assertThat(tvEpisodeDetails.getEpisodeName(), is("Walk of Punishment"));
        assertThat(tvEpisodeDetails.getShowName(), is("Game of Thrones"));
        assertThat(tvEpisodeDetails.getGenres(), is((List<String>) Lists.newArrayList("Adventure", "Drama", "Fantasy")));
    }

    @Test
    public void testFetchTvShowInformation() throws IMDBException {

        TvShowDetails tvShowDetails = imdb.getTvShowDetails("tt0944947");

        assertThat(tvShowDetails.getName(), is("Game of Thrones"));
        assertThat(tvShowDetails.getStartYear(), is(2011));
        assertThat(tvShowDetails.getEndYear(), is(-1));
        assertThat(tvShowDetails.getPlot(), is("Several noble families fight for control of the mythical land of Westeros."));
        assertThat(tvShowDetails.getRating(), is(9.5));
        assertThat(tvShowDetails.getDuration(), is(56));
        assertThat(tvShowDetails.getGenres(), is((Set<String>) Sets.newHashSet("Adventure", "Drama", "Fantasy")));
        assertThat(tvShowDetails.getCreators(), is((Set<String>) Sets.newHashSet("David Benioff", "D.B. Weiss")));
    }

    @Test
    public void testFetchTvShowInformation2() throws IMDBException {

        TvShowDetails tvShowDetails = imdb.getTvShowDetails("tt0455275");

        assertThat(tvShowDetails.getName(), is("Prison Break"));
        assertThat(tvShowDetails.getStartYear(), is(2005));
        assertThat(tvShowDetails.getEndYear(), is(2009));
        assertThat(tvShowDetails.getPlot(), is("Due to a political conspiracy, an innocent man is sent to death row and his only hope is his brother who makes it his mission to deliberately get himself sent to the same prison in order to break the both of them from the inside out."));
        assertThat(tvShowDetails.getRating(), is(8.5));
        assertThat(tvShowDetails.getDuration(), is(44));
        assertThat(tvShowDetails.getGenres(), is((Set<String>) Sets.newHashSet("Crime", "Action", "Drama")));

    }


}
