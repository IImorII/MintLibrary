package controller.command.action.account;

import controller.command.*;
import controller.command.show.MainPageCommand;
import dto.AccountDto;
import exception.CommandException;
import service.impl.AccountServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static controller.command.ControllerDestination.ERROR;
import static controller.command.ControllerDestination.MAIN;

public class LoginCommand implements Command {

    private static LoginCommand INSTANCE;

    private LoginCommand() {

    }

    public static LoginCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LoginCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            final String login = request.getStringParameter(ParameterDestination.LOGIN.getParameter());
            final String password = request.getStringParameter(ParameterDestination.PASSWORD.getParameter());
            final Optional<AccountDto> accountOptional = AccountServiceImpl.getInstance().login(login, password);
            if (accountOptional.isPresent()) {
                AccountDto account = accountOptional.get();
                HttpSession session = request.getSession();
                session.setAttribute(ParameterDestination.USER_ID.getParameter(), account.getId());
                session.setAttribute(ParameterDestination.USER_NAME.getParameter(), account.getName());
                session.setAttribute(ParameterDestination.USER_ROLE.getParameter(), account.getRole());
                session.setAttribute(ParameterDestination.BOOKS_CURRENT.getParameter(), account.getAmountCurrent());
                session.setAttribute(ParameterDestination.BOOKS_MAX.getParameter(), account.getAmountMax());
                session.setMaxInactiveInterval(300);
                return Command.of(CommandInstance.MAIN.name()).execute(request);
            } else {
                request.setAttribute(ParameterDestination.ERROR.getParameter(), "Incorrect login or password!");
                return() -> ERROR;
            }
        } catch (Exception ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
