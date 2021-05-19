package entity;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    private Integer id;
    private String name = "No name.";

    public BaseEntity() {
    }

    public BaseEntity(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public BaseEntity(Integer id) {
        this.setId(id);
        this.setName(null);
    }

    public BaseEntity(String name) {
        this.setId(null);
        this.setName(name);
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
