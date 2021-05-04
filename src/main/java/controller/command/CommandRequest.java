package controller.command;

import javax.servlet.http.HttpSession;

public interface CommandRequest {

    Object getAttribute(String name);
    Object getParameter(String name);
    HttpSession getSession();
    Object getSessionAttribute(String name);
}
