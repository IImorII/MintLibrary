package controller.command.role.librarian;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.Author;
import entity.Book;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import exception.ServiceException;
import service.AuthorService;
import service.BookService;
import service.Service;
import service.factory.ServiceInstance;

import static controller.command.ControllerDestination.ADD_AUTHOR_PANEL;
import static controller.command.ControllerDestination.ADD_BOOK_PANEL;

public class AddAuthorCommand implements Command {

    private static AddAuthorCommand INSTANCE;
    private AuthorService authorService;

    private AddAuthorCommand() {
        authorService = (AuthorService) Service.of(Author.class);
    }

    public static AddAuthorCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddAuthorCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        String name = request.getStringParameter(ParameterDestination.NAME.getParameter());
        Integer yearOfBirth = request.getIntParameter(ParameterDestination.YEAR.getParameter());
        String[] languagesId = request.getParameterValues(ParameterDestination.LANGUAGES.getParameter());
        try {
            authorService.createAuthor(name, yearOfBirth, languagesId);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return() -> ADD_AUTHOR_PANEL;
    }
}
