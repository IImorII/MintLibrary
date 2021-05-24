package controller.command.action.books;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.BookDto;
import exception.CommandException;
import service.impl.BookServiceImpl;

import static controller.command.ControllerDestination.BOOK;
import static controller.command.ControllerDestination.MAIN;

public class SingleBookCommand implements Command {

    private static SingleBookCommand INSTANCE;

    private SingleBookCommand() {

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
        BookDto book = BookServiceImpl.getInstance().getOne(bookIdParameter);
        request.setAttribute(ParameterDestination.BOOK.getParameter(), book);
        return () -> BOOK;
    }
}
