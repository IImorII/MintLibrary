package controller.command;

public enum ControllerDestination implements Destination {

    MAIN("/WEB-INF/jsp/main.jsp"),
    TEST("/WEB-INF/jsp/test.jsp"),
    BOOK("/WEB-INF/jsp/book.jsp"),
    LIBRARY_PANEL("/WEB-INF/jsp/libraryPanel/libraryPanel.jsp"),
    ADD_BOOK_PANEL("/WEB-INF/jsp/libraryPanel/addBookPanel.jsp"),
    CONFIRM_ORDER_PANEL("/WEB-INF/jsp/libraryPanel/confirmOrderPanel.jsp"),
    ERROR("/WEB-INF/jsp/error.jsp");

    private final String PATH;

    ControllerDestination(String path) {
        this.PATH = path;
    }

    @Override
    public String getPath() {
        return PATH;
    }

}
