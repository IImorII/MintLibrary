package entity;

import java.util.List;

public class Account extends BaseEntity {

    private static final long serialVersionUID = -4220525932797848862L;

    private String login;
    private String password;
    private Role role;
    private Integer bookAmountCurrent = 0;
    private Integer bookAmountMax = 10;
    private List<Book> books;

    public Account(Integer id, String name, String login, String password, Integer bookAmountCurrent, Integer bookAmountMax, Role role, List<Book> books) {
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        final int PRIME = 4111;
        int result = 1;
        result = PRIME + getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Account other = (Account) obj;
        return getId().equals(other.getId()) && getLogin().equals(other.getLogin());
    }
}
