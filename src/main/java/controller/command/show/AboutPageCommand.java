package controller.command.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import exception.CommandException;

import static controller.command.ControllerDestination.ABOUT;

public class AboutPageCommand implements Command {
    private static AboutPageCommand INSTANCE;

    private AboutPageCommand() {

    }

    public static AboutPageCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AboutPageCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        return () -> ABOUT;
    }
}
