package nl.stil4m.imdb.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TvEpisodeDetailsExceptionTest {

    @Test
    public void testConstructor() {
        Throwable throwable = mock(Throwable.class);
        TvEpisodeDetailsException tvEpisodeDetailsException = new TvEpisodeDetailsException("Message", throwable);
        assertThat(tvEpisodeDetailsException.getMessage(), is("Message"));
        assertThat(tvEpisodeDetailsException.getCause(), is(throwable));
    }
}
