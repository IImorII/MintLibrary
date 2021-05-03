package entity;

public class User extends BaseEntity {

    private String login;
    private String password;
    private Role role;

    public User(Integer id, String name, String login, String password, Role role) {
        this.setId(id);
        this.setName(name);
        this.setLogin(login);
        this.setPassword(password);
        this.setRole(role);
    }

    public User() {
        super();
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
