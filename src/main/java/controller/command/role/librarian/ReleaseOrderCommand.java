package controller.command.role.librarian;

import controller.command.*;
import exception.CommandException;
import service.AccountService;
import service.impl.AccountServiceImpl;

import static controller.command.CommandInstance.RELEASE_ORDER_PANEL;

public class ReleaseOrderCommand implements Command {

    private static ReleaseOrderCommand INSTANCE;
    private AccountService accountService;

    private ReleaseOrderCommand() {
        accountService = AccountServiceImpl.getInstance();
    }

    public static ReleaseOrderCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReleaseOrderCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
        Integer accountId = request.getIntParameter(ParameterDestination.ACCOUNT_ID.getParameter());
        accountService.releaseOrder(accountId, bookId);
        return Command.of(RELEASE_ORDER_PANEL).execute(request);
    }
}
