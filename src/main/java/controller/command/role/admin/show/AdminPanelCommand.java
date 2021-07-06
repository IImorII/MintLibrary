package controller.command.role.admin.show;

import controller.command.*;
import exception.CommandException;

import static controller.command.ControllerDestination.ADMIN_PANEL;
import static controller.command.ControllerDestination.LIBRARY_PANEL;

public class AdminPanelCommand implements Command {

    private static AdminPanelCommand INSTANCE;

    private AdminPanelCommand() {

    }

    public static AdminPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdminPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        return () -> ADMIN_PANEL;
    }
}