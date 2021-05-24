package controller.command.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import exception.CommandException;

import static controller.command.ControllerDestination.ADD_BOOK_PANEL;

public class AddBookPanelCommand implements Command {

    private static AddBookPanelCommand INSTANCE;

    private AddBookPanelCommand() {

    }

    public static AddBookPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddBookPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        return () -> ADD_BOOK_PANEL;
    }
}
