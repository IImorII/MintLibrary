package entity;

public class TicketType extends BaseEntity {
    Integer amountMax;
    public TicketType() {
        amountMax = 0;
    }

    public TicketType(Integer id, String name, Integer amountMax) {
        this.setId(id);
        this.setName(name);
        this.setAmountMax(amountMax);
    }

    public Integer getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(Integer amountMax) {
        this.amountMax = amountMax;
    }
}
