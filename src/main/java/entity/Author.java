package entity;

import java.util.List;

public class Author extends BaseEntity {

    private static final long serialVersionUID = 963031263366164503L;

    private Integer yearOfBirth;
    private List<Language> languages;

    public Author(Integer id, String name, Integer yearOfBirth, List<Language> languages) {
        setId(id);
        setName(name);
        setYearOfBirth(yearOfBirth);
        setLanguages(languages);
    }

    public Author() {

    }

    public Author(String name) {
        setName(name);
    }

    public Integer getYearOfBirth() {
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
