package controller.command.role.account;

import controller.command.Command;
import controller.command.CommandInstance;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import exception.CommandException;

public class LogoutCommand implements Command {

    private static LogoutCommand INSTANCE;

    private LogoutCommand() {

    }

    public static LogoutCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LogoutCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        request.invalidateSession();
        return Command.of(CommandInstance.MAIN.name()).execute(request);
    }
}
