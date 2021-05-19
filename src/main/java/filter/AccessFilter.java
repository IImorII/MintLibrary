package filter;

import controller.command.CommandAccess;
import controller.command.ParameterDestination;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        Object role = session.getAttribute(ParameterDestination.USER_ROLE.getParameter());
        String command = request.getParameter(ParameterDestination.COMMAND.getParameter());
        if (isAccess((String) role, command) || command == null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/");
        }
    }

    private boolean isAccess(String role, String command) {
        try {
            return (CommandAccess.valueOf(command.toUpperCase()).getRole().equalsIgnoreCase(role));
        } catch (IllegalArgumentException ex) {
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }
}
