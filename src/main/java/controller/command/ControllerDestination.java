package controller.command;

public enum ControllerDestination implements Destination {

    MAIN("/WEB-INF/jsp/main.jsp"),
    LOGIN("/WEB-INF/jsp/login.jsp"),
    ERROR("/WEB-IND/jsp/error.jsp");

    private final String PATH;

    ControllerDestination(String path) {
        this.PATH = path;
    }

    @Override
    public String getPath() {
        return PATH;
    }

}
