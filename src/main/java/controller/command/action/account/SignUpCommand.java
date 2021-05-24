package controller.command.action.account;

import controller.command.*;
import controller.command.show.MainPageCommand;
import dto.AccountDto;
import exception.CommandException;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static controller.command.ControllerDestination.ERROR;

public class SignUpCommand implements Command {

    private static SignUpCommand INSTANCE;

    private SignUpCommand() {

    }

    public static SignUpCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SignUpCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            final String login = request.getStringParameter(ParameterDestination.LOGIN.getParameter());
            final String password = request.getStringParameter(ParameterDestination.PASSWORD.getParameter());
            final String name = request.getStringParameter(ParameterDestination.USER_NAME.getParameter());
            final Optional<AccountDto> accountOptional = AccountServiceImpl.getInstance().signUp(login, password, name);
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
                request.setAttribute(ParameterDestination.ERROR.getParameter(), "Account with this login is already exists!");
                return() -> ERROR;
            }
        } catch (Exception ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
