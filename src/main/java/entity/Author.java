package entity;

import java.util.List;

public class Author extends BaseEntity {
    private Integer birth;
    private List<Language> languages;
    private List<Book> books;

    public Author(Integer id, String name, Integer birth, List<Language> languages, List<Book> books) {
        setId(id);
        setName(name);
        setBirth(birth);
        setLanguages(languages);
        setBooks(books);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
