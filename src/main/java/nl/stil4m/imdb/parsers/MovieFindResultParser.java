package nl.stil4m.imdb.parsers;

import nl.stil4m.imdb.domain.SearchResult;
import nl.stil4m.imdb.exceptions.ParseException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieFindResultParser implements Parser<SearchResult> {

    @Override
    public SearchResult parse(Element document) throws ParseException {
        SearchResult.SearchResultBuilder builder = SearchResult.builder();
        Element textResult = getTextResult(document);
        builder.id(getMovieId(textResult)).name(getMovieName(textResult)).year(0).type("Movie");
        String metaInfo = getMetaInfo(textResult);
        setupMetaInfo(builder, metaInfo);
        return builder.build();
    }

    private String getMovieId(Element textResult) {
        String href = textResult.getElementsByTag("a").get(0).attr("href");
        href = href.substring(0, href.indexOf("?"));
        if (href.endsWith("/")) {
            href = href.substring(0, href.length() - 1);
        }
        href = href.substring(href.lastIndexOf("/") + 1);
        return href;
    }

    private void setupMetaInfo(SearchResult.SearchResultBuilder builder, String metaInfo) {
        if (metaInfo.length() == 0) {
            return;
        }
        String[] components = metaInfo.substring(1, metaInfo.length() - 1).split("\\) \\(");
        if (components.length > 0) {
            int compIndex = 0;
            if (isVersionNumber(components[compIndex])) {
                compIndex++;
            }
            try {
                int year = Integer.parseInt(components[compIndex]);
                builder.year(year);
                if (components.length > compIndex + 1) {
                    builder.type(components[compIndex + 1]);
                }
            } catch (NumberFormatException e) {
                builder.type(components[compIndex]);
            }
        }
    }

    private boolean isVersionNumber(String component) {
        for (int i = 0; i < component.length(); i++) {
            if (!(component.charAt(i) == 'I')) {
                return false;
            }
        }
        return true;
    }

    private String getMovieName(Element textResult) {
        return textResult.select("a").get(0).text();
    }

    private Element getTextResult(Element document) throws ParseException {
        Elements textResults = document.select(".result_text");
        if (textResults.size() != 1) {
            throw new ParseException("Unexpected number of result_text elements");
        }
        return textResults.get(0);
    }

    public String getMetaInfo(Element textResult) {
        return textResult.ownText().substring(0, textResult.ownText().lastIndexOf(')') + 1);
    }

}
