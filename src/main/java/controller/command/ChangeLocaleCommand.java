package controller.command;

import exception.CommandException;

import static controller.command.ControllerDestination.MAIN;

public class ChangeLocaleCommand implements Command {
    private static ChangeLocaleCommand INSTANCE;

    private ChangeLocaleCommand() {
    }

    public static ChangeLocaleCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChangeLocaleCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        String language = request.getStringParameter(ParameterDestination.LANGUAGE.getParameter());
        if (language != null) {
            request.setSessionAttribute(ParameterDestination.LANGUAGE.getParameter(), language);
        }
        return Command.of(CommandInstance.MAIN).execute(request);
    }
}
