package entity;

public class Genre extends BaseEntity {

    private static final long serialVersionUID = -3461329200390517907L;

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
