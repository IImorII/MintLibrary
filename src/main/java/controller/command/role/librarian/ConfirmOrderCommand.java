package controller.command.role.librarian;

import controller.command.*;
import exception.CommandException;
import service.AccountService;
import service.impl.AccountServiceImpl;

import static controller.command.CommandInstance.CONFIRM_ORDER_PANEL;

public class ConfirmOrderCommand implements Command {

    private static ConfirmOrderCommand INSTANCE;
    private AccountService accountService;

    private ConfirmOrderCommand() {
        accountService = AccountServiceImpl.getInstance();
    }

    public static ConfirmOrderCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfirmOrderCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
        Integer userId = request.getIntParameter(ParameterDestination.USER_ID.getParameter());
        accountService.confirmOrder(userId, bookId);
        return Command.of(CONFIRM_ORDER_PANEL).execute(request);
    }
}
