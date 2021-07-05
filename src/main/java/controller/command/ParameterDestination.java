package controller.command;

public enum ParameterDestination {
    ACCOUNT("account"),
    ACCOUNTS_LIST("accountsList"),
    ACCOUNT_ID("accountId"),
    AUTHOR("author"),
    AUTHOR_ID("authorId"),
    AUTHORS("authors"),
    AUTHORS_LIST("authorsList"),
    BOOK("book"),
    BOOKS_CURRENT("booksCurrent"),
    BOOKS_LIST("booksList"),
    BOOKS_LIST_FULL("booksListFull"),
    BOOKS_MAX("booksMax"),
    BOOK_ID("bookId"),
    COMMAND("command"),
    CONFIRMED_BOOKS("confirmedBooks"),
    COUNT("count"),
    CURRENT_PAGE("page"),
    DESCRIPTION("description"),
    ERROR("error"),
    GENRE("genre"),
    GENRE_ID("genreId"),
    GENRES("genres"),
    GENRES_LIST("genresList"),
    IMAGE("image"),
    INFO("info"),
    LANGUAGE("language"),
    LANGUAGE_ID("languageId"),
    LANGUAGES("languages"),
    LANGUAGES_LIST("languagesList"),
    LAST_PAGE("lastPage"),
    LOGIN("login"),
    NAME("name"),
    PASSWORD("password"),
    PHOTO_URL("photoUrl"),
    ROLES_LIST("rolesList"),
    ROLE_ID("roleId"),
    SEARCH("search"),
    UNCONFIRMED_BOOKS("unconfirmedBooks"),
    USER("user"),
    USER_NAME("userName"),
    USER_ROLE("userRole"),
    YEAR("year");

    private final String parameter;

    ParameterDestination(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
