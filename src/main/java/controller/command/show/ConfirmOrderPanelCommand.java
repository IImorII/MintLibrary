package controller.command.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.BookDto;
import exception.CommandException;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static controller.command.ControllerDestination.CONFIRM_ORDER_PANEL;
import static controller.command.ControllerDestination.LIBRARY_PANEL;

public class ConfirmOrderPanelCommand implements Command {

    private static ConfirmOrderPanelCommand INSTANCE;

    private BookServiceImpl bookService;
    private AccountServiceImpl accountService;

    private ConfirmOrderPanelCommand() {
        bookService = BookServiceImpl.getInstance();
        accountService = AccountServiceImpl.getInstance();
    }

    public static ConfirmOrderPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfirmOrderPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Object userId = request.getParameter(ParameterDestination.USER_ID.getParameter());
        List<BookDto> books = new ArrayList<>();
        if (userId != null) {
            books = bookService.getUnconfirmedBooks(Integer.parseInt(userId.toString()));
            request.setAttribute(ParameterDestination.USER.getParameter(), accountService.getOne(Integer.parseInt(userId.toString())));
        }
        request.setAttribute(ParameterDestination.BOOKS_LIST.getParameter(), books);
        return () -> CONFIRM_ORDER_PANEL;
    }
}
