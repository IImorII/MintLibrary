package controller.command;

public enum ParameterDestination {
    USER_ID("userId"),
    USER_ROLE("userRole"),
    PAGE("page"),
    LAST_PAGE("lastPage"),
    ERROR("error"),
    SEARCH("search"),
    BOOKS_LIST("booksList");

    private final String parameter;

    ParameterDestination(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
