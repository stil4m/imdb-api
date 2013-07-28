package nl.stil4m.imdb.parsers;

import nl.stil4m.imdb.exceptions.ParseException;

import org.jsoup.nodes.Element;

public interface Parser<T> {

    T parse(Element document) throws ParseException;

}