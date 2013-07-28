package nl.stil4m.imdb.parsers;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import nl.stil4m.imdb.domain.TvEpisodeDetails;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.Properties;

public class TvEpisodeDetailsPageParser implements Parser<TvEpisodeDetails> {

    private static final String AIR_DATE = "TvEpisodeDetailsPageParser.airDate";
    private static final String SHOW_NAME = "TvEpisodeDetailsPageParser.showName";
    private static final String GENRES= "TvEpisodeDetailsPageParser.genres";
    private static final String EPISODE_NUMBER = "TvEpisodeDetailsPageParser.episodeNumber";
    private static final String SEASON_NUMBER = "TvEpisodeDetailsPageParser.seasonNumber";
    private static final String EPISODE_NAME = "TvEpisodeDetailsPageParser.episodeName";

    private final Properties properties;

    public TvEpisodeDetailsPageParser(Properties properties) {
        this.properties = properties;
    }

    @Override
    public TvEpisodeDetails parse(Element document) {
        String showName = getShowName(document);
        String episodeName = getEpisodeName(document);
        Long seasonNumber = getSeasonNumber(document);
        Long episodeNumber = getEpisodeNumber(document);
        List<String> genres = getGenres(document);
        Long airDate = getAirDate(document);

        return new TvEpisodeDetails(showName, episodeName, seasonNumber, episodeNumber, genres, airDate);
    }

    private Long getAirDate(Element document) {
        String dateStringWithBrackets = document.select(properties.get(AIR_DATE).toString()).text();
        String dateString = dateStringWithBrackets.substring(1, dateStringWithBrackets.length() - 1);
        return DateTime.parse(dateString, DateTimeFormat.forPattern("dd MMM. yyyy")).getMillis();
    }

    private List<String> getGenres(Element document) {
        String genreString = document.select(properties.get(GENRES).toString()).text();
        return Lists.transform(Lists.newArrayList(genreString.split(" ")), new Function<String, String>() {
            public String apply(String s) {
                return s.trim();
            }
        });
    }

    private Long getEpisodeNumber(Element document) {
        String episodeInfoText = document.select(properties.get(EPISODE_NUMBER).toString()).text();
        String episodeInfo = episodeInfoText.split(",")[1];
        return Long.parseLong(episodeInfo.replace("Episode", "").trim());
    }

    private Long getSeasonNumber(Element document) {
        String episodeInfoText = document.select(properties.get(SEASON_NUMBER).toString()).text();
        String seasonInfo = episodeInfoText.split(",")[0];
        return Long.parseLong(seasonInfo.replace("Season", "").trim());
    }

    private String getEpisodeName(Element document) {
        return document.select(properties.get(EPISODE_NAME).toString()).text();
    }

    private String getShowName(Element document) {
        return document.select(properties.get(SHOW_NAME).toString()).text();
    }

}
