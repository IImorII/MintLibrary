package controller.command;

public enum ParameterDestination {
    USER_ID("userId"),
    USER_ROLE("userRole"),
    LOGIN("login"),
    PASSWORD("password"),
    USER_NAME("userName"),
    BOOKS_MAX("booksMax"),
    BOOKS_CURRENT("booksCurrent"),
    CURRENT_PAGE("page"),
    LAST_PAGE("lastPage"),
    ERROR("error"),
    SEARCH("search"),
    COMMAND("command"),
    BOOKS_LIST_FULL("booksListFull"),
    BOOKS_LIST("booksList");

    private final String parameter;

    ParameterDestination(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
