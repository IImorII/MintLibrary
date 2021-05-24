package controller.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.List;

public interface CommandRequest {

    void setAttribute(String name, Object obj);

    Object getAttribute(String name);

    Object getParameter(String name);

    void invalidateSession();

    void setSessionAttribute(String name, Object obj);

    String getStringParameter(String name);

    Integer getIntParameter(String name);

    Object getSessionAttribute(String name);

    String getStringSessionAttribute(String name);

    Integer getIntSessionAttribute(String name);

    HttpSession getSession();

    Part getPart(String name);

    List<Part> getParts(String name);

    ServletContext getServletContext();
}
