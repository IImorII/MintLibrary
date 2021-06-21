package dto;

import java.util.List;

public class AuthorDto {
    Integer id;
    String name;
    Integer birth;
    List<String> languages;

    public AuthorDto(Integer id, String name, Integer birth, List<String> languages) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.languages = languages;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
