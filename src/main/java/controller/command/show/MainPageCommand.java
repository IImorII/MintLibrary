package controller.command.show;

import controller.command.*;
import controller.command.action.books.SwitchPageCommand;
import exception.CommandException;

import static controller.command.ControllerDestination.MAIN;

public class MainPageCommand implements Command {

    private static MainPageCommand INSTANCE;

    private MainPageCommand() {

    }

    public static MainPageCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainPageCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Object currentPage = request.getAttribute(ParameterDestination.CURRENT_PAGE.getParameter());
        if (currentPage == null) {
            return Command.of(CommandInstance.SWITCH_PAGE.name()).execute(request);
        }
        return () -> MAIN;
    }
}
