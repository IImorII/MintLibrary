package controller.command.show.books;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.BookDto;
import exception.CommandException;
import service.BookService;
import service.impl.BookServiceImpl;

import static controller.command.ControllerDestination.BOOK;
import static controller.command.ControllerDestination.MAIN;

public class SingleBookCommand implements Command {

    private static SingleBookCommand INSTANCE;
    private BookService bookService;
    private SingleBookCommand() {
        bookService = BookServiceImpl.getInstance();
    }

    public static SingleBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingleBookCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Integer bookIdParameter = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
        if (bookIdParameter == null) {
            return () -> MAIN;
        }
        BookDto book = bookService.getOne(bookIdParameter);
        request.setAttribute(ParameterDestination.BOOK.getParameter(), book);
        return () -> BOOK;
    }
}
