package controller.command;

import controller.command.action.account.LoginCommand;
import controller.command.action.account.LogoutCommand;
import controller.command.action.account.SignUpCommand;
import controller.command.action.books.SingleBookCommand;
import controller.command.action.librarian.AddBookCommand;
import controller.command.action.librarian.ConfirmOrderCommand;
import controller.command.action.librarian.DeleteBookCommand;
import controller.command.action.user.OrderBookCommand;
import controller.command.show.AddBookPanelCommand;
import controller.command.show.ConfirmOrderPanelCommand;
import controller.command.show.LibraryPanelCommand;
import controller.command.show.MainPageCommand;
import controller.command.action.books.SearchBookCommand;
import controller.command.action.books.SwitchPageCommand;

public enum CommandInstance {

    MAIN(MainPageCommand.getInstance()),
    LOGIN(LoginCommand.getInstance()),
    SEARCH_BOOK(SearchBookCommand.getInstance()),
    SHOW_BOOK(SingleBookCommand.getInstance()),
    SWITCH_PAGE(SwitchPageCommand.getInstance()),
    LOGOUT(LogoutCommand.getInstance()),
    ORDER_BOOK(OrderBookCommand.getInstance()),
    LIBRARY_PANEL(LibraryPanelCommand.getInstance()),
    ADD_BOOK_PANEL(AddBookPanelCommand.getInstance()),
    ADD_BOOK(AddBookCommand.getInstance()),
    DELETE_BOOK(DeleteBookCommand.getInstance()),
    CONFIRM_ORDER_PANEL(ConfirmOrderPanelCommand.getInstance()),
    CONFIRM_ORDER(ConfirmOrderCommand.getInstance()),
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
