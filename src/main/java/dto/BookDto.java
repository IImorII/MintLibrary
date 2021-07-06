package dto;

import java.util.List;

public class BookDto {
    private Integer id;
    private String name;
    private String description;
    private String photoUrl;
    private String language;
    private Integer count;
    private List<String> authorsNames;
    private List<String> genresNames;
    private Integer yearOfRelease;

    public BookDto(Integer id, Integer count, String name, String description, String photoUrl, String language, List<String> authors, List<String> genres, Integer yearOfRelease) {
        this.id = id;
        this.count = count;
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
        this.language = language;
        this.authorsNames = authors;
        this.genresNames = genres;
        this.yearOfRelease = yearOfRelease;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public String[] getAuthorsNames() {
        return authorsNames.toArray(new String[0]);
    }

    public List<String> getAuthorsNamesList() {
        return authorsNames;
    }

    public String[] getGenresNames() {
        return genresNames.toArray(new String[0]);
    }

    public List<String> getGenresNamesList() {
        return genresNames;
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

}
