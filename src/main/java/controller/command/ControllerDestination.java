package controller.command;

public enum ControllerDestination implements Destination {

    ABOUT("/WEB-INF/jsp/about.jsp"),
    ACCOUNTS_PANEL("/WEB-INF/jsp/admin/accountsPanel.jsp"),
    ADD_AUTHOR_PANEL("/WEB-INF/jsp/librarian/addAuthorPanel.jsp"),
    ADD_BOOK_PANEL("/WEB-INF/jsp/librarian/addBookPanel.jsp"),
    ADD_GENRE_PANEL("/WEB-INF/jsp/librarian/addGenrePanel.jsp"),
    ADD_LANGUAGE_PANEL("/WEB-INF/jsp/librarian/addLanguagePanel.jsp"),
    ADMIN_PANEL("/WEB-INF/jsp/admin/adminPanel.jsp"),
    BOOK("/WEB-INF/jsp/book.jsp"),
    CONFIRM_ORDER_PANEL("/WEB-INF/jsp/librarian/confirmOrderPanel.jsp"),
    INFO("/WEB-INF/jsp/info.jsp"),
    LIBRARY_PANEL("/WEB-INF/jsp/librarian/libraryPanel.jsp"),
    MAIN("/WEB-INF/jsp/main.jsp"),
    RELEASE_ORDER_PANEL("/WEB-INF/jsp/librarian/releaseOrderPanel.jsp"),
    SINGLE_ACCOUNT("/WEB-INF/jsp/admin/singleAccount.jsp"),
    TEST("/WEB-INF/jsp/test.jsp"),
    VIEW_ORDER_PANEL("/WEB-INF/jsp/user/viewOrderPanel.jsp");

    private final String PATH;

    ControllerDestination(String path) {
        this.PATH = path;
    }

    @Override
    public String getPath() {
        return PATH;
    }

}
