package controller.command;

public enum ControllerDestination implements Destination {

    MAIN("/WEB-INF/jsp/main.jsp"),
    TEST("/WEB-INF/jsp/test.jsp"),
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
