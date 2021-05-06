package controller.command;

import static controller.command.ControllerDestination.LOGIN;

public class LoginPageCommand implements Command {

    @Override
    public CommandResponse execute(CommandRequest request)  {
        return() -> LOGIN;
    }
}
