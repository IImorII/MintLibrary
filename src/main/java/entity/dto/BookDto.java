package entity.dto;

import java.util.List;

public class BookDto {
    private String name;
    private String description;
    private String photoUrl;
    private String language;
    private List<String> authors;
    private List<String> genres;
    private Integer yearOfRelease;

    public BookDto(String name, String description, String photoUrl, String language, List<String> authors, List<String> genres, Integer yearOfRelease) {
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.language = language;
        this.authors = authors;
        this.genres = genres;
        this.yearOfRelease = yearOfRelease;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public String getLanguage() {
        return language;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public List<String> getGenres() {
        return genres;
    }

}
