package controller.command;

import controller.command.action.account.LoginCommand;
import controller.command.action.account.LogoutCommand;
import controller.command.action.account.SignUpCommand;
import controller.command.show.MainPageCommand;
import controller.command.action.books.SearchBookCommand;
import controller.command.action.books.SwitchPageCommand;

public enum CommandInstance {

    MAIN(MainPageCommand.getInstance()),
    LOGIN(LoginCommand.getInstance()),
    SEARCH_BOOK(SearchBookCommand.getInstance()),
    SWITCH_PAGE(SwitchPageCommand.getInstance()),
    LOGOUT(LogoutCommand.getInstance()),
    SIGN_UP(SignUpCommand.getInstance());

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
