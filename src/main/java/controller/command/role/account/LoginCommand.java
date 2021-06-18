package controller.command.role.account;

import controller.command.*;
import dto.AccountDto;
import exception.CommandException;
import service.AccountService;
import service.impl.AccountServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static controller.command.ControllerDestination.INFO;

public class LoginCommand implements Command {

    private static LoginCommand INSTANCE;
    private AccountService accountService;

    private static String ERROR_MESSAGE = "Incorrect login or password!";

    private LoginCommand() {
        accountService = AccountServiceImpl.getInstance();
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
            final Optional<AccountDto> accountOptional = accountService.login(login, password);
            if (accountOptional.isPresent()) {
                AccountDto account = accountOptional.get();
                HttpSession session = request.getSession();
                session.setAttribute(ParameterDestination.ACCOUNT.getParameter(), account);
                session.setMaxInactiveInterval(300);
                return Command.of(CommandInstance.MAIN.name()).execute(request);
            } else {
                request.setAttribute(ParameterDestination.INFO.getParameter(), ERROR_MESSAGE);
                return() -> INFO;
            }
        } catch (Exception ex) {
            throw new CommandException(ex.getMessage());
        }
    }
}
