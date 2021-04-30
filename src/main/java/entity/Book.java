package entity;

import java.util.List;

public class Book extends BaseEntity {
    private int yearOfRelease;
    private Language language;
    private Ticket ticket;
    private List<Genre> genres;
    private List<Author> authors;
}
