package controller.command.role.user.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.AccountDto;
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
            AccountDto account = (AccountDto) request.getSessionAttribute(ParameterDestination.ACCOUNT.getParameter());
            List<BookDto> confirmedBooks = new ArrayList<>();
            List<BookDto> unconfirmedBooks = new ArrayList<>();
            if (account != null) {
                confirmedBooks = bookService.getConfirmedBooks(account.getId());
                unconfirmedBooks = bookService.getUnconfirmedBooks(account.getId());
            }
            request.setAttribute(ParameterDestination.CONFIRMED_BOOKS.getParameter(), confirmedBooks);
            request.setAttribute(ParameterDestination.UNCONFIRMED_BOOKS.getParameter(), unconfirmedBooks);
        } catch (Exception ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> VIEW_ORDER_PANEL;
    }
}
