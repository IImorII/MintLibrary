package controller.command.show;

import controller.command.*;
import exception.CommandException;

import static controller.command.ControllerDestination.ABOUT;
import static controller.command.ControllerDestination.MAIN;

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
