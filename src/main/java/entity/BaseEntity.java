package entity;

public abstract class BaseEntity {
    private Integer id;
    private String name;

    public BaseEntity() {
        this.setId(null);
        this.setName(null);
    }

    public BaseEntity(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public BaseEntity(Integer id) {
        this.setId(id);
        this.setName(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
