package controller.command.role.admin;

import controller.command.*;
import exception.CommandException;
import service.AccountService;
import service.impl.AccountServiceImpl;

public class ChangeRoleCommand implements Command {
    private static ChangeRoleCommand INSTANCE;
    private AccountService accountService;

    private ChangeRoleCommand() {
        accountService = AccountServiceImpl.getInstance();
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
