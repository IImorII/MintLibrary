package controller.command;

import controller.command.role.account.LoginCommand;
import controller.command.role.account.LogoutCommand;
import controller.command.role.account.SignUpCommand;
import controller.command.role.admin.ChangeRoleCommand;
import controller.command.role.admin.DeleteAccountCommand;
import controller.command.role.admin.show.AccountsPanelCommand;
import controller.command.role.admin.show.SingleAccountCommand;
import controller.command.role.books.SingleBookCommand;
import controller.command.role.librarian.*;
import controller.command.role.librarian.show.*;
import controller.command.role.user.OrderBookCommand;
import controller.command.role.user.RemoveOrderCommand;
import controller.command.role.user.show.ViewOrderPanelCommand;
import controller.command.show.*;
import controller.command.role.books.SearchBookCommand;
import controller.command.role.books.SwitchPageCommand;

public enum CommandInstance {
    ABOUT(AboutPageCommand.getInstance()),
    ACCOUNTS_PANEL(AccountsPanelCommand.getInstance()),
    ADD_BOOK(AddBookCommand.getInstance()),
    ADD_BOOK_PANEL(AddBookPanelCommand.getInstance()),
    ADD_AUTHOR_PANEL(AddAuthorPanelCommand.getInstance()),
    ADD_AUTHOR(AddAuthorCommand.getInstance()),
    ADD_GENRE_PANEL(AddGenrePanelCommand.getInstance()),
    ADD_LANGUAGE_PANEL(AddLanguagePanelCommand.getInstance()),
    CHANGE_ROLE(ChangeRoleCommand.getInstance()),
    CONFIRM_ORDER(ConfirmOrderCommand.getInstance()),
    CONFIRM_ORDER_PANEL(ConfirmOrderPanelCommand.getInstance()),
    DELETE_ACCOUNT(DeleteAccountCommand.getInstance()),
    DELETE_BOOK(DeleteBookCommand.getInstance()),
    LIBRARY_PANEL(LibraryPanelCommand.getInstance()),
    LOGIN(LoginCommand.getInstance()),
    LOGOUT(LogoutCommand.getInstance()),
    MAIN(MainPageCommand.getInstance()),
    ORDER_BOOK(OrderBookCommand.getInstance()),
    RELEASE_ORDER(ReleaseOrderCommand.getInstance()),
    RELEASE_ORDER_PANEL(ReleaseOrderPanelCommand.getInstance()),
    REMOVE_ORDER(RemoveOrderCommand.getInstance()),
    SEARCH_BOOK(SearchBookCommand.getInstance()),
    SHOW_BOOK(SingleBookCommand.getInstance()),
    SIGN_UP(SignUpCommand.getInstance()),
    SINGLE_ACCOUNT(SingleAccountCommand.getInstance()),
    SWITCH_PAGE(SwitchPageCommand.getInstance()),
    VIEW_ORDER_PANEL(ViewOrderPanelCommand.getInstance());

    private final Command command;

    CommandInstance(Command command) {
        this.command = command;
    }

    static Command of(String name) {
        for (CommandInstance c : values()) {
            if (c.name().equalsIgnoreCase(name)) {
                return c.command;
            }
        }
        return MAIN.command;
    }
}
