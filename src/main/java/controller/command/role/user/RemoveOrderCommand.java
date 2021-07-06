package controller.command.role.user;

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
        try {
            Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
            AccountDto account = (AccountDto) request.getSessionAttribute(ParameterDestination.ACCOUNT.getParameter());
            Integer accountId = account.getId();
            List<BookDto> unconfirmedBooks = bookService.getUnconfirmedBooks(accountId);
            for (BookDto book : unconfirmedBooks) {
                if (book.getId().equals(bookId)) {
                    accountService.releaseOrder(accountId, bookId);
                    account = accountService.getOne(accountId);
                    request.setSessionAttribute(ParameterDestination.ACCOUNT.getParameter(), account);
                }
            }
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(VIEW_ORDER_PANEL).execute(request);
    }
}
