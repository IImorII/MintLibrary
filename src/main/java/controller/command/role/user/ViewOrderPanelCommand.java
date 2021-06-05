package controller.command.role.user;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.BookDto;
import exception.CommandException;
import service.AccountService;
import service.BookService;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static controller.command.ControllerDestination.CONFIRM_ORDER_PANEL;
import static controller.command.ControllerDestination.VIEW_ORDER_PANEL;

public class ViewOrderPanelCommand implements Command {

    private static ViewOrderPanelCommand INSTANCE;

    private BookService bookService;
    private AccountService accountService;

    private ViewOrderPanelCommand() {
        bookService = BookServiceImpl.getInstance();
        accountService = AccountServiceImpl.getInstance();
    }

    public static ViewOrderPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ViewOrderPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Object userId = request.getParameter(ParameterDestination.USER_ID.getParameter());
        List<BookDto> confirmedBooks = new ArrayList<>();
        List<BookDto> unconfirmedBooks = new ArrayList<>();
        if (userId != null) {
            confirmedBooks = bookService.getConfirmedBooks(Integer.parseInt(userId.toString()));
            unconfirmedBooks = bookService.getConfirmedBooks(Integer.parseInt(userId.toString()));
            request.setAttribute(ParameterDestination.USER.getParameter(), accountService.getOne(Integer.parseInt(userId.toString())));
        }
        request.setAttribute(ParameterDestination.CONFIRMED_BOOKS.getParameter(), confirmedBooks);
        request.setAttribute(ParameterDestination.UNCONFIRMED_BOOKS.getParameter(), unconfirmedBooks);
        return () -> VIEW_ORDER_PANEL;
    }
}
