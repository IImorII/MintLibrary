package controller.command.role.librarian;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.Book;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import exception.ServiceException;
import service.BookService;
import service.Service;
import service.factory.ServiceInstance;

import static controller.command.ControllerDestination.ADD_BOOK_PANEL;

public class AddBookCommand implements Command {

    private static AddBookCommand INSTANCE;
    private BookService bookService;

    private AddBookCommand() {
        bookService = (BookService) Service.of(Book.class);
    }

    public static AddBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddBookCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        String name = request.getStringParameter(ParameterDestination.NAME.getParameter());
        String photoUrl = request.getStringParameter(ParameterDestination.PHOTO_URL.getParameter());
        String description = request.getStringParameter(ParameterDestination.DESCRIPTION.getParameter());
        Integer year = request.getIntParameter(ParameterDestination.YEAR.getParameter());
        Integer count = request.getIntParameter(ParameterDestination.COUNT.getParameter());
        String[] authorsId = request.getParameterValues(ParameterDestination.AUTHORS.getParameter());
        String[] genresId = request.getParameterValues(ParameterDestination.GENRES.getParameter());
        String languageId = request.getStringParameter(ParameterDestination.LANGUAGE.getParameter());
        try {
            bookService.createBook(name, description, authorsId, genresId, languageId, photoUrl, count, year);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return() -> ADD_BOOK_PANEL;
    }
}
