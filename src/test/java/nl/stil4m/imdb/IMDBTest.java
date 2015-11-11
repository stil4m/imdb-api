package nl.stil4m.imdb;

import com.google.common.collect.Lists;

import nl.stil4m.imdb.commands.Command;
import nl.stil4m.imdb.commands.SearchTitleCommand;
import nl.stil4m.imdb.commands.TitleDetailsCommand;
import nl.stil4m.imdb.domain.MovieDetails;
import nl.stil4m.imdb.domain.SearchResult;
import nl.stil4m.imdb.exceptions.IMDBException;
import nl.stil4m.imdb.exceptions.ParseException;
import nl.stil4m.imdb.filter.Predicate;
import nl.stil4m.imdb.parsers.MovieDetailsPageParser;
import nl.stil4m.imdb.parsers.SearchedMoviesParser;
import nl.stil4m.imdb.parsers.TvEpisodeDetailsPageParser;
import nl.stil4m.imdb.parsers.TvShowDetailsPageParser;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IMDBTest {

    private IMDB imdb;

    @Mock
    private DocumentBuilder documentBuilder;

    @Mock
    private SearchedMoviesParser searchedMoviesParser;

    @Mock
    private MovieDetailsPageParser movieDetailsPageParser;

    @Mock
    private TvShowDetailsPageParser tvShowDetailsPageParser;

    @Mock
    private TvEpisodeDetailsPageParser tvEpisodeDetailsPageParser;

    @Mock
    private Document document;

    @Captor
    private ArgumentCaptor<Command> commandCaptor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        imdb = new IMDB(documentBuilder, searchedMoviesParser, movieDetailsPageParser, tvShowDetailsPageParser, tvEpisodeDetailsPageParser);
    }

    @org.junit.Test
    public void testSearch() throws IMDBException, IOException, ParseException {
        List<SearchResult> answer = Lists.newArrayList(mock(SearchResult.class), mock(SearchResult.class), mock(SearchResult.class));
        when(documentBuilder.buildDocument(isA(Command.class))).thenReturn(document);
        when(searchedMoviesParser.parse(document)).thenReturn(answer);
        List<SearchResult> result = imdb.search("someQuery");

        verify(documentBuilder).buildDocument(commandCaptor.capture());

        assertThat(commandCaptor.getValue() instanceof SearchTitleCommand, is(true));
        assertThat(result, is(answer));
    }

    @org.junit.Test
    public void testSearchWithFilter() throws IMDBException, IOException, ParseException {
        SearchResult first = mock(SearchResult.class);
        SearchResult second = mock(SearchResult.class);

        @SuppressWarnings("unchecked")
        Predicate<SearchResult> predicate = mock(Predicate.class);
        when(predicate.accepts(first)).thenReturn(true);
        when(predicate.accepts(second)).thenReturn(false);

        List<SearchResult> answer = Lists.newArrayList(first, second);
        when(documentBuilder.buildDocument(isA(Command.class))).thenReturn(document);
        when(searchedMoviesParser.parse(document)).thenReturn(answer);

        List<SearchResult> result = imdb.search("someQuery", predicate);

        verify(documentBuilder).buildDocument(commandCaptor.capture());
        assertThat(commandCaptor.getValue() instanceof SearchTitleCommand, is(true));
        assertThat(result, is((List<SearchResult>) Lists.newArrayList(first)));
    }

    @Test
    public void testSearchWithIOException() throws IOException {
        doThrow(IOException.class).when(documentBuilder).buildDocument(isA(Command.class));

        try {
            imdb.search("someQuery");
            fail();
        } catch (IMDBException e) {
            assertThat(e.getCause() instanceof IOException, is(true));
            assertThat(e.getMessage(), is("Could not find movies for name 'someQuery'"));
        }
    }

    @Test
    public void testSearchWithParseException() throws IOException {
        doThrow(ParseException.class).when(documentBuilder).buildDocument(isA(Command.class));
        try {
            imdb.search("someQuery");
            fail();
        } catch (IMDBException e) {
            assertThat(e.getCause() instanceof ParseException, is(true));
            assertThat(e.getMessage(), is("A parse exception occurred while searching movies for name 'someQuery'"));
        }
    }

    @Test
    public void testGetMovieDetails() throws IOException, IMDBException {
        MovieDetails answer = mock(MovieDetails.class);
        when(documentBuilder.buildDocument(isA(Command.class))).thenReturn(document);
        when(movieDetailsPageParser.parse(document)).thenReturn(answer);

        MovieDetails result = imdb.getMovieDetails("someId");

        verify(documentBuilder).buildDocument(commandCaptor.capture());

        assertThat(commandCaptor.getValue() instanceof TitleDetailsCommand, is(true));
        assertThat(((TitleDetailsCommand) commandCaptor.getValue()).getId(), is("someId"));
        assertThat(result, is(answer));

    }
}
