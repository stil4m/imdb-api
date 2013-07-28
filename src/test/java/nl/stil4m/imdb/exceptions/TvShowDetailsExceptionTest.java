package nl.stil4m.imdb.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TvShowDetailsExceptionTest {

    @Test
    public void testConstructor() {
        Throwable throwable = mock(Throwable.class);
        TvShowDetailsException tvShowDetailsException  = new TvShowDetailsException("Message", throwable);
        assertThat(tvShowDetailsException.getMessage(), is("Message"));
        assertThat(tvShowDetailsException.getCause(), is(throwable));
    }
}
