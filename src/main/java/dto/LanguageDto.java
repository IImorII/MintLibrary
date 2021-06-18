package dto;

public class LanguageDto {
    Integer id;
    String name;

    public LanguageDto(Integer id, String name) {
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
