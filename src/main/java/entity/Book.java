package entity;

import java.util.List;

public class Book extends BaseEntity {
    private int yearOfRelease;
    private Language language;
    private List<Ticket> tickets;
    private Double rate;
    private Integer count;
    private List<Genre> genres;
    private List<Author> authors;

    public Book(int id, String name, int yearOfRelease, Language language, Double rate, Integer count) {
        setId(id);
        setName(name);
        setYearOfRelease(yearOfRelease);
        setLanguage(language);
        setRate(rate);
        setCount(count);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
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
}
