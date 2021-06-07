package controller.command.role.admin.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import controller.command.role.librarian.show.ConfirmOrderPanelCommand;
import dto.AccountDto;
import dto.BookDto;
import exception.CommandException;
import service.AccountService;
import service.BookService;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static controller.command.ControllerDestination.ACCOUNTS_PANEL;
import static controller.command.ControllerDestination.CONFIRM_ORDER_PANEL;

public class AccountsPanelCommand implements Command {
    private static AccountsPanelCommand INSTANCE;

    private AccountService accountService;

    private AccountsPanelCommand() {
        accountService = AccountServiceImpl.getInstance();
    }

    public static AccountsPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountsPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        List<AccountDto> accounts;
        accounts = accountService.getAll();
        accounts = accountService.removeAdmins(accounts);
        request.setAttribute(ParameterDestination.ACCOUNTS_LIST.getParameter(), accounts);
        return () -> ACCOUNTS_PANEL;
    }
}
