package controller.command.role.user;

import controller.command.*;
import dto.BookDto;
import exception.CommandException;
import service.AccountService;
import service.BookService;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;
import java.util.List;

import static controller.command.CommandInstance.VIEW_ORDER_PANEL;


public class RemoveOrderCommand implements Command {
    private static RemoveOrderCommand INSTANCE;
    private final BookService bookService;
    private final AccountService accountService;

    private RemoveOrderCommand() {
        bookService = BookServiceImpl.getInstance();
        accountService = AccountServiceImpl.getInstance();
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
