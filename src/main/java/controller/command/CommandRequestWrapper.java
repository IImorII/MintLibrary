package controller.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CommandRequestWrapper implements CommandRequest {

    private final HttpServletRequest request;

    private CommandRequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setAttribute(String name, Object obj) {
        request.setAttribute(name, obj);
    }

    @Override
    public Object getAttribute(String name) {
        return request.getAttribute(name);
    }

    @Override
    public Part getPart(String name) {
        Part part = null;
        try {
            part = request.getPart(name);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
        return part;
    }

    @Override
    public List<Part> getParts(String name) {
        List<Part> parts = null;
        try {
            parts = (List<Part>) request.getParts();
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
        return parts;
    }

    @Override
    public ServletContext getServletContext() {
        return request.getServletContext();
    }

    @Override
    public Object getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public void invalidateSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void setSessionAttribute(String name, Object obj) {
        final HttpSession session = request.getSession(true);
        session.setAttribute(name, obj);
    }

    @Override
    public String getStringParameter(String name) {
        return String.valueOf(getParameter(name));
    }

    @Override
    public Integer getIntParameter(String name) {
        Integer intParameter;
        try {
            intParameter = Integer.parseInt(getStringParameter(name));
        } catch (NumberFormatException ex) {
            return null;
        }
        return intParameter;
    }

    @Override
    public Object getSessionAttribute(String name) {
        return getSession().getAttribute(name);
    }

    @Override
    public String getStringSessionAttribute(String name) {
        return String.valueOf(getSessionAttribute(name));
    }

    @Override
    public Integer getIntSessionAttribute(String name) {
        return Integer.parseInt(getStringSessionAttribute(name));
    }

    @Override
    public HttpSession getSession() {
        return request.getSession(false);
    }

    public static CommandRequest of(HttpServletRequest request) {
        return new CommandRequestWrapper(request);
    }
}
