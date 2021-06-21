package controller.command.role.admin;

import controller.command.*;
import entity.Account;
import exception.CommandException;
import service.AccountService;
import service.Service;
import service.factory.ServiceInstance;

public class ChangeRoleCommand implements Command {
    private static ChangeRoleCommand INSTANCE;
    private AccountService accountService;

    private ChangeRoleCommand() {
        accountService = (AccountService) Service.of(Account.class);
    }

    public static ChangeRoleCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ChangeRoleCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer accountId = request.getIntParameter(ParameterDestination.ACCOUNT_ID.getParameter());
            Integer roleId = request.getIntParameter(ParameterDestination.ROLE_ID.getParameter());
            if (accountId != null && roleId != null) {
                accountService.changeAccountRole(accountId, roleId);
            }
        } catch (Exception ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.ACCOUNTS_PANEL).execute(request);
    }
}
