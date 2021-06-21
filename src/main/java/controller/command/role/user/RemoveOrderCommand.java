package controller.command.role.user;

import controller.command.*;
import dto.BookDto;
import entity.Account;
import entity.Book;
import exception.CommandException;
import service.AccountService;
import service.BookService;
import service.Service;
import service.factory.ServiceInstance;

import java.util.List;

import static controller.command.CommandInstance.VIEW_ORDER_PANEL;


public class RemoveOrderCommand implements Command {
    private static RemoveOrderCommand INSTANCE;
    private final BookService bookService;
    private final AccountService accountService;

    private RemoveOrderCommand() {
        bookService = (BookService) Service.of(Book.class);
        accountService = (AccountService) Service.of(Account.class);
    }

    public static RemoveOrderCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoveOrderCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
        Integer accountId = request.getIntSessionAttribute(ParameterDestination.ACCOUNT_ID.getParameter());
        List<BookDto> unconfirmedBooks = bookService.getUnconfirmedBooks(accountId);
        for (BookDto book : unconfirmedBooks) {
            if (book.getId().equals(bookId)) {
                accountService.releaseOrder(accountId, bookId);
                request.setSessionAttribute(ParameterDestination.BOOKS_CURRENT.getParameter(),
                        accountService.getOne(accountId).getAmountCurrent());
            }
        }
        return Command.of(VIEW_ORDER_PANEL).execute(request);
    }
}
