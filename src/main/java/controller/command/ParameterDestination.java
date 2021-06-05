package controller.command;

public enum ParameterDestination {
    USER_ID("userId"),
    USER_ROLE("userRole"),
    LOGIN("login"),
    NAME("name"),
    PASSWORD("password"),
    USER_NAME("userName"),
    BOOKS_MAX("booksMax"),
    BOOKS_CURRENT("booksCurrent"),
    CURRENT_PAGE("page"),
    LAST_PAGE("lastPage"),
    IMAGE("image"),
    PHOTO_URL("photoUrl"),
    DESCRIPTION("description"),
    AUTHOR("author"),
    GENRE("genre"),
    LANGUAGE("language"),
    COUNT("count"),
    YEAR("year"),
    USER("user"),
    ERROR("error"),
    INFO("info"),
    SEARCH("search"),
    COMMAND("command"),
    BOOKS_LIST_FULL("booksListFull"),
    BOOKS_LIST("booksList"),
    UNCONFIRMED_BOOKS("unconfirmedBooks"),
    CONFIRMED_BOOKS("confirmedBooks"),
    ACCOUNTS_LIST("accountsList"),
    BOOK("book"),
    BOOK_ID("bookId");

    private final String parameter;

    ParameterDestination(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
