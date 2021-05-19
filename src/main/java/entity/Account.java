package entity;

public class Account extends BaseEntity {

    private static final long serialVersionUID = -4220525932797848862L;

    private String login;
    private String password;
    private Role role;
    private Integer bookAmountCurrent = 0;
    private Integer bookAmountMax = 10;

    public Account(Integer id, String name, String login, String password, Integer bookAmountCurrent, Integer bookAmountMax, Role role) {
        setId(id);
        setName(name);
        setLogin(login);
        setPassword(password);
        setBookAmountCurrent(bookAmountCurrent);
        setBookAmountMax(bookAmountMax);
        setRole(role);
    }

    public Account() {
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

    public Integer getBookAmountCurrent() {
        return bookAmountCurrent;
    }

    public void setBookAmountCurrent(Integer bookAmountCurrent) {
        this.bookAmountCurrent = bookAmountCurrent;
    }

    public Integer getBookAmountMax() {
        return bookAmountMax;
    }

    public void setBookAmountMax(Integer bookAmountMax) {
        this.bookAmountMax = bookAmountMax;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
