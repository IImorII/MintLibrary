package controller.command.role.librarian;

import controller.command.*;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import service.BookService;
import service.impl.BookServiceImpl;

public class DeleteBookCommand implements Command {

    private static DeleteBookCommand INSTANCE;
    private BookService bookService;

    private DeleteBookCommand() {
        bookService = BookServiceImpl.getInstance();
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
        } catch (DaoException | ConnectionException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.MAIN).execute(request);
    }
}
