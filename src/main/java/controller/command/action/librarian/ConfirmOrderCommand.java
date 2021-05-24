package controller.command.action.librarian;

import controller.command.*;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;

import static controller.command.ControllerDestination.ADD_BOOK_PANEL;
import static controller.command.ControllerDestination.CONFIRM_ORDER_PANEL;

public class ConfirmOrderCommand implements Command {

    private static ConfirmOrderCommand INSTANCE;

    private ConfirmOrderCommand() {

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
        AccountServiceImpl.getInstance().ConfirmOrder(userId, bookId);
        return Command.of(CommandInstance.CONFIRM_ORDER_PANEL.name()).execute(request);
    }
}
