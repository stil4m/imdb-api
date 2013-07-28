package nl.stil4m.imdb.parsers;

import nl.stil4m.imdb.domain.TvShowDetails;
import nl.stil4m.imdb.util.ElementUtil;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TvShowDetailsPageParser implements Parser<TvShowDetails> {

    private static final String CREATORS = "TvShowDetailsPageParser.creators";
    private static final String NAME = "TvShowDetailsPageParser.name";
    private static final String PLOT = "TvShowDetailsPageParser.plot";
    private static final String DURATION = "TvShowDetailsPageParser.duration";
    private static final String GENRES = "TvShowDetailsPageParser.genres";
    private static final String YEAR = "TvShowDetailsPageParser.year";
    private static final String RATING = "TvShowDetailsPageParser.rating";

    private static final char separatorChar = 8211;
    private static final Pattern YEAR_PATTERN = Pattern.compile("\\(([0-9]{4})" + separatorChar + "([0-9]{4}| )\\)");

    private final Properties properties;
    private final ElementUtil elementUtil;

    public TvShowDetailsPageParser(Properties properties, ElementUtil elementUtil) {
        this.properties = properties;
        this.elementUtil = elementUtil;
    }

    @Override
    public TvShowDetails parse(Element document) {
        String plot = getPlot(document);
        String name = getName(document);
        Double rating = getRating(document);
        Integer startYear = getStartYear(document);
        Integer endYear = getEndYear(document);
        Integer duration = getDuration(document);
        List<String> genres = getGenres(document);
        List<String> creators = getCreators(document);
        return new TvShowDetails(name, rating, startYear, endYear, duration, genres, plot, creators);
    }

    private List<String> getCreators(Element document) {
        return elementUtil.allTextForElements(
                document.select(properties.get(CREATORS).toString())
        );
    }

    private List<String> getGenres(Element document) {
        String genreString = document.select(properties.get(GENRES).toString()).text();
        List<String> answer = new ArrayList<String>();
        for (String genre : genreString.split(" ")) {
            answer.add(genre.trim());
        }
        return answer;
    }

    private Integer getDuration(Element document) {
        return Integer.parseInt(document.select(properties.get(DURATION).toString()).text().replace(" min", ""));
    }

    private Integer getEndYear(Element document) {
        String yearString = document.select(properties.get(YEAR).toString()).text();
        Matcher matcher = YEAR_PATTERN.matcher(yearString);
        matcher.find();
        if (matcher.group(2).equals(" ")) {
            return -1;
        }
        return Integer.parseInt(matcher.group(2));
    }

    private Integer getStartYear(Element document) {
        String yearString = document.select(properties.get(YEAR).toString()).text();
        Matcher matcher = YEAR_PATTERN.matcher(yearString);
        matcher.find();
        return Integer.parseInt(matcher.group(1));
    }

    private Double getRating(Element document) {
        return Double.parseDouble(document.select(properties.get(RATING).toString()).text());
    }

    private String getName(Element document) {
        return document.select(properties.get(NAME).toString()).text();
    }

    private String getPlot(Element document) {
        return document.select(properties.get(PLOT).toString()).text();
    }

}
