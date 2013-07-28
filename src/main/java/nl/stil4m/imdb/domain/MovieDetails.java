package nl.stil4m.imdb.domain;

import java.util.List;

public class MovieDetails {

    private final String movieName;

    private final Integer year;

    private final List<String> categories;

    private final Double rating;

    private final String description;

    private final List<String> directors;

    private final List<String> writers;
    
    private final List<String> stars;

    private final String image;

    public MovieDetails(String movieName, Integer year, String description, Double rating, List<String> directors, List<String> writers, List<String> stars, List<String> categories, String image) {
        this.movieName = movieName;
        this.year = year;
        this.description = description;
        this.rating = rating;
        this.directors = directors;
        this.writers = writers;
        this.stars = stars;
        this.categories = categories;
        this.image = image;
    }

    public String getMovieName() {
        return movieName;
    }

    public Integer getYear() {
        return year;
    }

    public List<String> getCategories() {
        return categories;
    }

    public Double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public String getImage() {
        return image;
    }
}
