package controller.command;

public enum CommandAccess {
    ACCOUNTS_PANEL(CommandRoles.ADMIN),
    ADD_AUTHOR(CommandRoles.LIBRARIAN),
    ADD_AUTHOR_PANEL(CommandRoles.LIBRARIAN),
    ADD_BOOK(CommandRoles.LIBRARIAN),
    ADD_BOOK_PANEL(CommandRoles.LIBRARIAN),
    ADD_GENRE(CommandRoles.LIBRARIAN),
    ADD_GENRE_PANEL(CommandRoles.LIBRARIAN),
    ADD_LANGUAGE(CommandRoles.LIBRARIAN),
    ADD_LANGUAGE_PANEL(CommandRoles.LIBRARIAN),
    CHANGE_ROLE(CommandRoles.ADMIN),
    CONFIRM_ORDER(CommandRoles.LIBRARIAN),
    CONFIRM_ORDER_PANEL(CommandRoles.LIBRARIAN),
    DELETE_ACCOUNT(CommandRoles.ADMIN),
    DELETE_BOOK(CommandRoles.LIBRARIAN),
    LIBRARY_PANEL(CommandRoles.LIBRARIAN),
    RELEASE_ORDER_PANEL(CommandRoles.LIBRARIAN),
    SINGLE_ACCOUNT(CommandRoles.ADMIN);

    private final CommandRoles role;

    public String getRole() {
        return role.getRole();
    }

    CommandAccess(CommandRoles role) {
        this.role = role;
    }
}
