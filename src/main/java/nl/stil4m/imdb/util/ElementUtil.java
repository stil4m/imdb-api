package nl.stil4m.imdb.util;

import org.jsoup.select.Elements;

import java.util.List;
import java.util.Set;

public interface ElementUtil {

    List<String> allTextForElements(Elements elements);

    Set<String> allTextForElementsSet(Elements elements);
}
