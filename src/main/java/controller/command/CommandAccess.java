package controller.command;

public enum CommandAccess {
    LIBRARY_PANEL(CommandRoles.LIBRARIAN),
    ADD_BOOK_PANEL(CommandRoles.LIBRARIAN),
    CONFIRM_ORDER_PANEL(CommandRoles.LIBRARIAN),
    ADD_BOOK(CommandRoles.LIBRARIAN),
    DELETE_BOOK(CommandRoles.LIBRARIAN),
    CONFIRM_ORDER(CommandRoles.LIBRARIAN);

    private final CommandRoles role;

    public String getRole() {
        return role.getRole();
    }

    CommandAccess(CommandRoles role) {
        this.role = role;
    }
}
