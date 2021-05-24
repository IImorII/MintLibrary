package controller.command.action.user;

import controller.command.*;
import controller.command.action.books.SwitchPageCommand;
import exception.CommandException;
import service.impl.AccountServiceImpl;

import static controller.command.ControllerDestination.BOOK;
import static controller.command.ControllerDestination.ERROR;

public class OrderBookCommand implements Command {

    private static OrderBookCommand INSTANCE;

    private OrderBookCommand() {

    }

    public static OrderBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderBookCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        AccountServiceImpl accountService = AccountServiceImpl.getInstance();
        Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
        Integer userId = (Integer) request.getSessionAttribute(ParameterDestination.USER_ID.getParameter());
        System.out.println(userId + " " + bookId);
        request.setAttribute(ParameterDestination.ERROR.getParameter(), accountService.OrderBook(userId, bookId) ? "Success order!" : "Fail order!");
        request.setSessionAttribute(ParameterDestination.BOOKS_CURRENT.getParameter(), accountService.getOne(userId).getAmountCurrent());
        return() -> ERROR;
    }
}
