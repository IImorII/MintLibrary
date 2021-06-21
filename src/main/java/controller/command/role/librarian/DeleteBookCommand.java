package controller.command.role.librarian;

import controller.command.*;
import entity.Book;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import exception.ServiceException;
import service.BookService;
import service.Service;
import service.factory.ServiceInstance;

public class DeleteBookCommand implements Command {

    private static DeleteBookCommand INSTANCE;
    private BookService bookService;

    private DeleteBookCommand() {
        bookService = (BookService) Service.of(Book.class);
    }

    public static DeleteBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeleteBookCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
            bookService.deleteBook(bookId);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.MAIN).execute(request);
    }
}
