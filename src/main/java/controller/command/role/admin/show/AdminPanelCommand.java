package controller.command.role.admin.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import exception.CommandException;

import static controller.command.ControllerDestination.ADMIN_PANEL;

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