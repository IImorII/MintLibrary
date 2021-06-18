package filter;

import controller.command.CommandAccess;
import controller.command.ParameterDestination;
import dto.AccountDto;

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
        AccountDto account = (AccountDto) session.getAttribute(ParameterDestination.ACCOUNT.getParameter());
        String command = request.getParameter(ParameterDestination.COMMAND.getParameter());
        if (command == null || isAccess(account, command) ) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/");
        }
    }

    private boolean isAccess(AccountDto account, String command) {
        try {
            return (CommandAccess.valueOf(command.toUpperCase()).getRole().equalsIgnoreCase(account.getRole()));
        } catch (IllegalArgumentException ex) {
            return true;
        } catch (NullPointerException ex) {
            return false;
        }
    }
}
