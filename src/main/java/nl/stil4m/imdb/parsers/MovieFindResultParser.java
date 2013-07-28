package nl.stil4m.imdb.parsers;

import nl.stil4m.imdb.domain.SearchResult;
import nl.stil4m.imdb.exceptions.ParseException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MovieFindResultParser implements Parser<SearchResult> {

    @Override
    public SearchResult parse(Element document) throws ParseException {
        Element textResult = getTextResult(document);
        SearchResult searchResult = new SearchResult(getMovieId(textResult), getMovieName(textResult), 0, "Movie");
        String metaInfo = getMetaInfo(textResult);
        setupMetaInfo(searchResult, metaInfo);
        return searchResult;
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

    private void setupMetaInfo(SearchResult searchResult, String metaInfo) {
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
                Integer year = Integer.parseInt(components[compIndex]);
                searchResult.setYear(year);
                if (components.length > compIndex + 1) {
                    searchResult.setType(components[compIndex + 1]);
                }
            } catch (NumberFormatException e) {
                searchResult.setType(components[compIndex]);
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
