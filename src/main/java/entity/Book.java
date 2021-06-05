package entity;

import java.util.List;

public class Book extends BaseEntity {

    private static final long serialVersionUID = -7433720986607565670L;

    private int yearOfRelease;
    private Language language;
    private Double rate = 0.0;
    private Integer count = 0;
    private String description = "No description.";
    private String photoUrl = "img/BookDefault.png";
    private List<Account> accounts;
    private List<Genre> genres;
    private List<Author> authors;

    public Book(int id, String name, String description, String photoUrl, Integer yearOfRelease, Double rate, Integer count,
                Language language, List<Genre> genres, List<Author> authors, List<Account> accounts) {
        setId(id);
        setName(name);
        setDescription(description);
        setPhotoUrl(photoUrl);
        setYearOfRelease(yearOfRelease);
        setRate(rate);
        setCount(count);
        setLanguage(language);
        setGenres(genres);
        setAuthors(authors);
        setAccounts(accounts);
    }

    public Book() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        final int PRIME = 9001;
        int result = 1;
        result = PRIME + getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Book other = (Book) obj;
        return getId().equals(other.getId()) && getName().equals(other.getName());
    }
}
