package nl.stil4m.imdb.util.impl;

import nl.stil4m.imdb.util.ElementUtil;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultElementUtil implements ElementUtil {

    @Override
    public List<String> allTextForElements(Elements elements) {
        List<String> strings = new ArrayList<>();
        for (Element element : elements) {
            strings.add(element.text());
        }
        return strings;
    }

    @Override
    public Set<String> allTextForElementsSet(Elements elements) {
        List<String> result = allTextForElements(elements);
        return result.stream().collect(Collectors.<String>toSet());
    }
}
