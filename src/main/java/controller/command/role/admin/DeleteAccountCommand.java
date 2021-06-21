package controller.command.role.admin;

import controller.command.*;
import entity.Account;
import exception.CommandException;
import service.AccountService;
import service.Service;
import service.factory.ServiceInstance;

public class DeleteAccountCommand implements Command {
    private static DeleteAccountCommand INSTANCE;
    private AccountService accountService;

    private DeleteAccountCommand() {
        accountService = (AccountService) Service.of(Account.class);
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
            Integer accountId = request.getIntParameter(ParameterDestination.ACCOUNT_ID.getParameter());
            if (accountId != null) {
                accountService.deleteAccount(accountId);
            }
        } catch (Exception ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.ACCOUNTS_PANEL).execute(request);
    }
}
