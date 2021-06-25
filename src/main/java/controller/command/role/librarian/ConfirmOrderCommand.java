package controller.command.role.librarian;

import controller.command.*;
import entity.Account;
import exception.CommandException;
import exception.ServiceException;
import service.AccountService;
import service.Service;
import service.factory.ServiceInstance;

import static controller.command.CommandInstance.CONFIRM_ORDER_PANEL;

public class ConfirmOrderCommand implements Command {

    private static ConfirmOrderCommand INSTANCE;
    private AccountService accountService;

    private ConfirmOrderCommand() {
        accountService = (AccountService) Service.of(Account.class);
    }

    public static ConfirmOrderCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfirmOrderCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
            Integer accountId = request.getIntParameter(ParameterDestination.ACCOUNT_ID.getParameter());
            accountService.confirmOrder(accountId, bookId);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CONFIRM_ORDER_PANEL).execute(request);
    }
}
