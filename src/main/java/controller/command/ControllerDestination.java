package controller.command;

public enum ControllerDestination implements Destination {

    MAIN("/WEB-INF/jsp/main.jsp"),
    TEST("/WEB-INF/jsp/test.jsp"),
    BOOK("/WEB-INF/jsp/book.jsp"),
    LIBRARY_PANEL("/WEB-INF/jsp/librarian/libraryPanel.jsp"),
    ADMIN_PANEL("/WEB-INF/jsp/admin/adminPanel.jsp"),
    ACCOUNTS_PANEL("/WEB-INF/jsp/admin/accountsPanel.jsp"),
    ADD_BOOK_PANEL("/WEB-INF/jsp/librarian/addBookPanel.jsp"),
    CONFIRM_ORDER_PANEL("/WEB-INF/jsp/librarian/confirmOrderPanel.jsp"),
    RELEASE_ORDER_PANEL("/WEB-INF/jsp/librarian/releaseOrderPanel.jsp"),
    VIEW_ORDER_PANEL("/WEB-INF/jsp/user/viewOrderPanel.jsp"),
    ABOUT("/WEB-INF/jsp/about.jsp"),
    INFO("/WEB-INF/jsp/info.jsp");

    private final String PATH;

    ControllerDestination(String path) {
        this.PATH = path;
    }

    @Override
    public String getPath() {
        return PATH;
    }

}
