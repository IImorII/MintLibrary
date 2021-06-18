package controller.command;

public enum ParameterDestination {
    ACCOUNT_ID("accountId"),
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
    AUTHORS("authors"),
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
    ROLES_LIST("rolesList"),
    ACCOUNT("account"),
    BOOK("book"),
    BOOK_ID("bookId"),
    GENRES("genres"),
    ROLE_ID("roleId");

    private final String parameter;

    ParameterDestination(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
