package controller.command.role.librarian;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.Account;
import exception.CommandException;
import exception.ServiceException;
import service.AccountService;
import service.Service;

import static controller.command.CommandInstance.RELEASE_ORDER_PANEL;

public class ReleaseOrderCommand implements Command {

    private static ReleaseOrderCommand INSTANCE;
    private AccountService accountService;

    private ReleaseOrderCommand() {
        accountService = (AccountService) Service.of(Account.class);
    }

    public static ReleaseOrderCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReleaseOrderCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
            Integer accountId = request.getIntParameter(ParameterDestination.ACCOUNT_ID.getParameter());
            accountService.releaseOrder(accountId, bookId);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(RELEASE_ORDER_PANEL).execute(request);
    }
}
