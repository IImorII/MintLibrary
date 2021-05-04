package controller.command;

public class LoginSubmitCommand implements Command {

    public static final CommandResponse RESPONSE = new CommandResponse() {
        @Override
        public Boolean isRedirect() {
            return true;
        }

        @Override
        public Destination getDestination() {
            return null;
        }
    };

    @Override
    public CommandResponse execute(CommandRequest request) {
        return null;
    }
}
