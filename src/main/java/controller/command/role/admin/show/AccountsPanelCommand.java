package controller.command.role.admin.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.AccountDto;
import entity.Account;
import exception.CommandException;
import exception.ServiceException;
import service.AccountService;
import service.Service;
import service.factory.ServiceInstance;

import java.util.List;

import static controller.command.ControllerDestination.ACCOUNTS_PANEL;

public class AccountsPanelCommand implements Command {
    private static AccountsPanelCommand INSTANCE;

    private AccountService accountService;

    private AccountsPanelCommand() {
        accountService = (AccountService) Service.of(Account.class);
    }

    public static AccountsPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountsPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            List<AccountDto> accounts;
            accounts = accountService.getAll();
            accounts = accountService.removeAdmins(accounts);
            request.setAttribute(ParameterDestination.ACCOUNTS_LIST.getParameter(), accounts);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> ACCOUNTS_PANEL;
    }
}
