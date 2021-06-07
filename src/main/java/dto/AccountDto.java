package dto;

public class AccountDto {
    private Integer id;
    private String login;
    private String name;
    private String role;
    private Integer amountCurrent;
    private Integer amountMax;

    public AccountDto(Integer id, String login, String name, String role, Integer amountCurrent, Integer amountMax) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.role = role;
        this.amountCurrent = amountCurrent;
        this.amountMax = amountMax;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Integer getAmountCurrent() {
        return amountCurrent;
    }

    public Integer getAmountMax() {
        return amountMax;
    }
}
