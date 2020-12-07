package nl.stil4m.imdb.parsers;

import nl.stil4m.imdb.domain.TvShowDetails;
import nl.stil4m.imdb.util.ElementUtil;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
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
        Set<String> genres = getGenres(document);
        Set<String> creators = getCreators(document);
        return new TvShowDetails(name, rating, startYear, endYear, duration, genres, plot, creators);
    }

    private Set<String> getCreators(Element document) {
        return elementUtil.allTextForElementsSet(
                document.select(properties.get(CREATORS).toString())
        );
    }

    private Set<String> getGenres(Element document) {
        String genreString = document.select(properties.get(GENRES).toString()).text().split(Pattern.quote("|"))[1].trim();
        Set<String> answer = new HashSet<>();
        for (String genre : genreString.split(",")) {
            answer.add(genre.trim());
        }
        return answer;
    }

    private Integer getDuration(Element document) {
        return Integer.parseInt(document.select(properties.get(DURATION).toString()).text().split(Pattern.quote("|"))[0].replace("min", "").trim());
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
        final String subtitle = document.select(properties.get(YEAR).toString()).text();

        String yearString = subtitle.split(Pattern.quote("|"))[2];
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
