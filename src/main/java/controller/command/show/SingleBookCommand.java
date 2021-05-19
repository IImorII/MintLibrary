package controller.command.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import exception.CommandException;

public class SingleBookCommand implements Command {
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        return null;
    }
}
