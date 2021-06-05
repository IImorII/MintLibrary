package controller.command.role.user;

import controller.command.*;
import exception.CommandException;
import service.AccountService;
import service.impl.AccountServiceImpl;

import static controller.command.ControllerDestination.ERROR;

public class OrderBookCommand implements Command {

    private static OrderBookCommand INSTANCE;
    private AccountService accountService;
    private static String SUCCESS_MESSAGE = "Success order!";
    private static String ERROR_MESSAGE = "Fail order!";

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
        Integer userId = (Integer) request.getSessionAttribute(ParameterDestination.USER_ID.getParameter());
        request.setAttribute(ParameterDestination.ERROR.getParameter(), accountService.orderBook(userId, bookId) ? SUCCESS_MESSAGE : ERROR_MESSAGE);
        request.setSessionAttribute(ParameterDestination.BOOKS_CURRENT.getParameter(), accountService.getOne(userId).getAmountCurrent());
        return() -> ERROR;
    }
}
