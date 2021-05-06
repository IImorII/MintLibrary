package controller.command;

import exception.CommandException;

public interface Command {
    CommandResponse execute (CommandRequest request) throws CommandException;
    static Command of(String name) {
        return CommandInstance.of(name);
    }
}
