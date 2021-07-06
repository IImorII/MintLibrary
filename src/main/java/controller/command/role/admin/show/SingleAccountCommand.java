package controller.command.role.admin.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.AccountDto;
import dto.RoleDto;
import entity.Account;
import entity.Role;
import exception.CommandException;
import exception.ServiceException;
import service.AccountService;
import service.RoleService;
import service.Service;
import java.util.List;

import static controller.command.CommandInstance.ACCOUNTS_PANEL;
import static controller.command.ControllerDestination.*;


public class SingleAccountCommand implements Command {
    private static SingleAccountCommand INSTANCE;

    private AccountService accountService;
    private RoleService roleService;

    private SingleAccountCommand() {
        accountService = (AccountService) Service.of(Account.class);
        roleService = (RoleService) Service.of(Role.class);
    }

    public static SingleAccountCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingleAccountCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer accountId = request.getIntParameter(ParameterDestination.ACCOUNT_ID.getParameter());
            if (accountId == null) {
                return Command.of(ACCOUNTS_PANEL).execute(request);
            }
            AccountDto account = accountService.getOne(accountId);
            List<RoleDto> roles = roleService.getAllWithoutAdmin();
            request.setAttribute(ParameterDestination.ACCOUNT.getParameter(), account);
            request.setAttribute(ParameterDestination.ROLES_LIST.getParameter(), roles);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> SINGLE_ACCOUNT;
    }
}
