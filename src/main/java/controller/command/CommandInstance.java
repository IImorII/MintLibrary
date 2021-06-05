package controller.command;

import controller.command.role.account.LoginCommand;
import controller.command.role.account.LogoutCommand;
import controller.command.role.account.SignUpCommand;
import controller.command.role.books.SingleBookCommand;
import controller.command.role.librarian.AddBookCommand;
import controller.command.role.librarian.ConfirmOrderCommand;
import controller.command.role.librarian.DeleteBookCommand;
import controller.command.role.librarian.ReleaseOrderCommand;
import controller.command.role.librarian.show.AddBookPanelCommand;
import controller.command.role.librarian.show.ConfirmOrderPanelCommand;
import controller.command.role.librarian.show.LibraryPanelCommand;
import controller.command.role.librarian.show.ReleaseOrderPanelCommand;
import controller.command.role.user.OrderBookCommand;
import controller.command.role.user.RemoveOrderCommand;
import controller.command.role.user.show.ViewOrderPanelCommand;
import controller.command.show.*;
import controller.command.role.books.SearchBookCommand;
import controller.command.role.books.SwitchPageCommand;

public enum CommandInstance {

    MAIN(MainPageCommand.getInstance()),
    LOGIN(LoginCommand.getInstance()),
    ABOUT(AboutPageCommand.getInstance()),
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
    RELEASE_ORDER_PANEL(ReleaseOrderPanelCommand.getInstance()),
    VIEW_ORDER_PANEL(ViewOrderPanelCommand.getInstance()),
    CONFIRM_ORDER(ConfirmOrderCommand.getInstance()),
    RELEASE_ORDER(ReleaseOrderCommand.getInstance()),
    REMOVE_ORDER(RemoveOrderCommand.getInstance()),
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
