package dto;

public class AccountDto {
    private String name;
    private String role;
    private Integer amountCurrent;
    private Integer amountMax;

    public AccountDto(String name, String role, Integer amountCurrent, Integer amountMax) {
        this.name = name;
        this.role = role;
        this.amountCurrent = amountCurrent;
        this.amountMax = amountMax;
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
