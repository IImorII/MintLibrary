package controller.command.role.admin;

import controller.command.*;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import service.AccountService;
import service.impl.AccountServiceImpl;

public class DeleteAccountCommand implements Command {
    private static DeleteAccountCommand INSTANCE;
    private AccountService accountService;

    private DeleteAccountCommand() {
        accountService = AccountServiceImpl.getInstance();
    }

    public static DeleteAccountCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeleteAccountCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer accountId = request.getIntParameter(ParameterDestination.USER_ID.getParameter());
            if (accountId != null) {
                accountService.deleteAccount(accountId);
            }
        } catch (Exception ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.ACCOUNTS_PANEL).execute(request);
    }
}
