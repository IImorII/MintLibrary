package entity;

public class Role extends BaseEntity {

    private static final long serialVersionUID = -6468857359242895677L;

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
