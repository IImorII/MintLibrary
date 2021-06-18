package controller.command.role.account;

import controller.command.*;
import dto.AccountDto;
import exception.CommandException;
import service.AccountService;
import service.impl.AccountServiceImpl;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static controller.command.ControllerDestination.INFO;

public class SignUpCommand implements Command {

    private static SignUpCommand INSTANCE;
    private AccountService accountService;
    private static String ERROR_MESSAGE = "Account with this login is already exists!";

    private SignUpCommand() {
        accountService = AccountServiceImpl.getInstance();
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
            final Optional<AccountDto> accountOptional = accountService.signUp(login, password, name);
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
