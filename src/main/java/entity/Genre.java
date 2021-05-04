package entity;

public class Genre extends BaseEntity {
    public Genre() {
        super();
    }
    public Genre(String name) {
        super(name);
    }

    public Genre(Integer id, String name) {
        super(id, name);
    }
}
