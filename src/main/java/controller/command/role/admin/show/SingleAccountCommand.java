package controller.command.role.admin.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.AccountDto;
import dto.BookDto;
import dto.RoleDto;
import exception.CommandException;
import service.AccountService;
import service.RoleService;
import service.impl.AccountServiceImpl;
import service.impl.RoleServiceImpl;

import java.util.List;

import static controller.command.CommandInstance.ACCOUNTS_PANEL;
import static controller.command.ControllerDestination.*;


public class SingleAccountCommand implements Command {
    private static SingleAccountCommand INSTANCE;

    private AccountService accountService;
    private RoleService roleService;

    private SingleAccountCommand() {
        accountService = AccountServiceImpl.getInstance();
        roleService = RoleServiceImpl.getInstance();
    }

    public static SingleAccountCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingleAccountCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Integer accountId = request.getIntParameter(ParameterDestination.ACCOUNT_ID.getParameter());
        if (accountId == null) {
            return Command.of(ACCOUNTS_PANEL).execute(request);
        }
        AccountDto account = accountService.getOne(accountId);
        List<RoleDto> roles = roleService.getAll();
        request.setAttribute(ParameterDestination.ACCOUNT.getParameter(), account);
        request.setAttribute(ParameterDestination.ROLES_LIST.getParameter(), roles);
        return () -> SINGLE_ACCOUNT;
    }
}
