package controller.command;

import static controller.command.ControllerDestination.MAIN;

public class MainPageCommand implements Command {

    @Override
    public CommandResponse execute(CommandRequest request) {
        return() -> MAIN;
    }
}
