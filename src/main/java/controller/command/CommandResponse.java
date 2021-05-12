package controller.command;

public interface CommandResponse {
    default Boolean isRedirect() {
        return false;
    }
    Destination getDestination();
}
