package controller.command;

public enum CommandInstance {

    MAIN(new MainPageCommand()),
    LOGIN(new LoginPageCommand()),
    SUBMIT_LOGIN(new LoginSubmitCommand());

    private final Command command;

    CommandInstance(Command command) {
        this.command = command;
    }

    static Command of(String name) {
        for (CommandInstance c: values()) {
            if (c.name().equalsIgnoreCase(name)) {
                return c.command;
            }
        }
        return MAIN.command;
    }
}
