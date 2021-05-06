package entity;

import java.util.List;

public class Ticket extends BaseEntity {
    private Integer amountCurrent;
    private TicketType type;
    private List<Book> books;

    public Ticket(Integer id, String name, TicketType type, Integer amountCurrent, List<Book> books) {
        this.setId(id);
        this.setName(name);
        this.setAmountCurrent(amountCurrent);
        this.setType(type);
        this.setBooks(books);
    }

    public Integer getAmountCurrent() {
        return amountCurrent;
    }

    public void setAmountCurrent(Integer amountCurrent) {
        this.amountCurrent = amountCurrent;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }


    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
