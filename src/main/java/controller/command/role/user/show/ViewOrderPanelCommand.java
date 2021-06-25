package controller.command.role.user.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.BookDto;
import entity.Account;
import entity.Book;
import exception.CommandException;
import exception.ServiceException;
import service.AccountService;
import service.BookService;
import service.Service;
import service.factory.ServiceInstance;

import java.util.ArrayList;
import java.util.List;

import static controller.command.ControllerDestination.VIEW_ORDER_PANEL;

public class ViewOrderPanelCommand implements Command {

    private static ViewOrderPanelCommand INSTANCE;

    private final BookService bookService;
    private final AccountService accountService;

    private ViewOrderPanelCommand() {
        bookService = (BookService) Service.of(Book.class);
        accountService = (AccountService) Service.of(Account.class);
    }

    public static ViewOrderPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ViewOrderPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Object accountId = request.getSessionAttribute(ParameterDestination.ACCOUNT_ID.getParameter());
            List<BookDto> confirmedBooks = new ArrayList<>();
            List<BookDto> unconfirmedBooks = new ArrayList<>();
            if (accountId != null) {
                confirmedBooks = bookService.getConfirmedBooks(Integer.parseInt(accountId.toString()));
                unconfirmedBooks = bookService.getUnconfirmedBooks(Integer.parseInt(accountId.toString()));
                request.setAttribute(ParameterDestination.USER.getParameter(), accountService.getOne(Integer.parseInt(accountId.toString())));
            }
            request.setAttribute(ParameterDestination.CONFIRMED_BOOKS.getParameter(), confirmedBooks);
            request.setAttribute(ParameterDestination.UNCONFIRMED_BOOKS.getParameter(), unconfirmedBooks);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> VIEW_ORDER_PANEL;
    }
}
