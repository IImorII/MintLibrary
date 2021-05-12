package controller.command;

import javax.servlet.http.HttpSession;

import static controller.command.ControllerDestination.MAIN;

public class LoginSubmitCommand implements Command {

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Object login = request.getParameter("login");
        final Object password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        HttpSession session = request.getSession();
        session.setAttribute("login", login.toString());
        session.setAttribute("password", password.toString());
        session.setMaxInactiveInterval(10);
        return() -> MAIN;
    }
}
