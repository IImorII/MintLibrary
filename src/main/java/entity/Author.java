package entity;

import java.util.List;

public class Author extends BaseEntity {

    private static final long serialVersionUID = 963031263366164503L;

    private Integer yearOfBirth;
    private List<Language> languages;
    private List<Book> books;

    public Author(Integer id, String name, Integer yearOfBirth, List<Language> languages, List<Book> books) {
        setId(id);
        setName(name);
        setYearOfBirth(yearOfBirth);
        setLanguages(languages);
        setBooks(books);
    }

    public Author() {

    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
