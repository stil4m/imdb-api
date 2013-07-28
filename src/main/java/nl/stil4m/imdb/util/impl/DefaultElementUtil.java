package nl.stil4m.imdb.util.impl;

import nl.stil4m.imdb.util.ElementUtil;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DefaultElementUtil implements ElementUtil {

    @Override
    public List<String> allTextForElements(Elements elements) {
        List<String> strings = new ArrayList<>();
        for (Element element : elements) {
            strings.add(element.text());
        }
        return strings;
    }
}
