package nl.stil4m.imdb.util;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public interface ElementUtil {

    List<String> allTextForElements(Elements elements);
}
