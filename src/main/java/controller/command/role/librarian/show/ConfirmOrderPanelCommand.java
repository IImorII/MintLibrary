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

import static controller.command.ControllerDestination.CONFIRM_ORDER_PANEL;

public class  ConfirmOrderPanelCommand implements Command {

    private static ConfirmOrderPanelCommand INSTANCE;

    private BookService bookService;
    private AccountService accountService;

    private ConfirmOrderPanelCommand() {
        bookService = (BookService) Service.of(Book.class);
        accountService = (AccountService) Service.of(Account.class);
    }

    public static ConfirmOrderPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConfirmOrderPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Object accountId = request.getParameter(ParameterDestination.ACCOUNT_ID.getParameter());
            List<BookDto> books = new ArrayList<>();
            if (accountId != null) {
                books = bookService.getUnconfirmedBooks(Integer.parseInt(accountId.toString()));
                request.setAttribute(ParameterDestination.ACCOUNT.getParameter(), accountService.getOne(Integer.parseInt(accountId.toString())));
            }
            request.setAttribute(ParameterDestination.BOOKS_LIST.getParameter(), books);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> CONFIRM_ORDER_PANEL;
    }
}
