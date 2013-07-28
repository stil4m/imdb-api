package nl.stil4m.imdb.filter;

public interface Predicate<T> {

    boolean accepts(T input);
}
