package controller;

import controller.command.*;
import exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/app")
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class AppController extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AppController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            final String command = req.getParameter(ParameterDestination.COMMAND.getParameter());
            final Command commandName = Command.of(command);
            final CommandResponse result = commandName.execute(CommandRequestWrapper.of(req));
            if (result.isRedirect()) {
                resp.sendRedirect(req.getContextPath() + result.getDestination().getPath());
            } else {
                final RequestDispatcher dispatcher = req.getRequestDispatcher(result.getDestination().getPath());
                dispatcher.forward(req, resp);
            }
        } catch (CommandException ex) {
            LOGGER.error(ex.getMessage());
            req.setAttribute(ParameterDestination.ERROR.getParameter(), ex.getMessage());
            final RequestDispatcher dispatcher = req.getRequestDispatcher(ControllerDestination.ERROR.getPath());
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
