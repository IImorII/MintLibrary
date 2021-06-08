package controller.command.role.user;

import controller.command.*;
import exception.CommandException;
import service.AccountService;
import service.impl.AccountServiceImpl;

import static controller.command.ControllerDestination.INFO;

public class OrderBookCommand implements Command {

    private static OrderBookCommand INSTANCE;
    private AccountService accountService;

    private OrderBookCommand() {
        accountService = AccountServiceImpl.getInstance();
    }

    public static OrderBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderBookCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
        Integer accountId = (Integer) request.getSessionAttribute(ParameterDestination.ACCOUNT_ID.getParameter());
        request.setAttribute(ParameterDestination.INFO.getParameter(), accountService.orderBook(accountId, bookId));
        request.setSessionAttribute(ParameterDestination.BOOKS_CURRENT.getParameter(), accountService.getOne(accountId).getAmountCurrent());
        return() -> INFO;
    }
}
