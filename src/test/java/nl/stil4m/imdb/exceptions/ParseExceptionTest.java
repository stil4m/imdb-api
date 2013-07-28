package nl.stil4m.imdb.exceptions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class ParseExceptionTest {

    @Test
    public void testReasonConstructor() {
        ParseException parseException = new ParseException("Some Reason");
        assertThat(parseException.getCause(), is(nullValue()));
        assertThat(parseException.getMessage(), is("Some Reason"));
    }

    @Test
    public void testReasonAndCauseConstructor() {
        Throwable throwable = mock(Throwable.class);
        ParseException parseException = new ParseException("Some Reason", throwable);
        assertThat(parseException.getCause(), is(throwable));
        assertThat(parseException.getMessage(), is("Some Reason"));
    }
}
