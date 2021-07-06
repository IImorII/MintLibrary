package controller.command.role.librarian.show;

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

import java.util.ArrayList;
import java.util.List;

import static controller.command.ControllerDestination.RELEASE_ORDER_PANEL;


public class ReleaseOrderPanelCommand implements Command {
    private static ReleaseOrderPanelCommand INSTANCE;

    private BookService bookService;
    private AccountService accountService;

    private ReleaseOrderPanelCommand() {
        bookService = (BookService) Service.of(Book.class);;
        accountService = (AccountService) Service.of(Account.class);;
    }

    public static ReleaseOrderPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReleaseOrderPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Object accountId = request.getParameter(ParameterDestination.ACCOUNT_ID.getParameter());
            List<BookDto> books = new ArrayList<>();
            if (accountId != null) {
                books = bookService.getConfirmedBooks(Integer.parseInt(accountId.toString()));
                request.setAttribute(ParameterDestination.ACCOUNT.getParameter(), accountService.getOne(Integer.parseInt(accountId.toString())));
            }
            request.setAttribute(ParameterDestination.BOOKS_LIST.getParameter(), books);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> RELEASE_ORDER_PANEL;
    }
}
