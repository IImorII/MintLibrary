package controller.command.role.librarian.show;

import controller.command.*;
import exception.CommandException;

import static controller.command.ControllerDestination.LIBRARY_PANEL;

public class LibraryPanelCommand implements Command {

    private static LibraryPanelCommand INSTANCE;

    private LibraryPanelCommand() {

    }

    public static LibraryPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LibraryPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        return () -> LIBRARY_PANEL;
    }
}
