package nl.stil4m.imdb.exceptions;

public class IMDBException extends Throwable {

    public IMDBException(String message, Throwable e) {
        super(message, e);
    }

}
