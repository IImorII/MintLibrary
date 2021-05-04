package controller.command;

import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface CommandResponse {
    default Boolean isRedirect() {
        return false;
    }
    Destination getDestination();
}
