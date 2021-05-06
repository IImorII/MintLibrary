package entity;

public class Role extends BaseEntity {
    public Role() {
        super();
    }

    public Role(String name) {
        super(name);
    }

    public Role(Integer id, String name) {
        super(id, name);
    }
}
