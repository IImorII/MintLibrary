package controller.command;

public interface Command {
    CommandResponse execute (CommandRequest request);
    static Command of(String name) {
        return CommandInstance.of(name);
    }
}
