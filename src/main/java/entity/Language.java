package entity;

public class Language extends BaseEntity {

    private static final long serialVersionUID = -4362112488018835195L;

    public Language() {
        super();
    }

    public Language(String name) {
        super(name);
    }

    public Language(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
