package dto;

import java.util.List;

public class AuthorDto {
    Integer id;
    String name;
    Integer birth;
    List<String> languages;

    public AuthorDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
