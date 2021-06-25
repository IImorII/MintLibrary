package controller.command.role.librarian;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.Author;
import entity.Book;
import entity.Genre;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import exception.ServiceException;
import service.AuthorService;
import service.BookService;
import service.GenreService;
import service.Service;
import service.factory.ServiceInstance;

import static controller.command.ControllerDestination.ADD_BOOK_PANEL;
import static controller.command.ControllerDestination.ADD_GENRE_PANEL;

public class AddGenreCommand implements Command {

    private static AddGenreCommand INSTANCE;
    private GenreService genreService;

    private AddGenreCommand() {
        genreService = (GenreService) Service.of(Genre.class);
    }

    public static AddGenreCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddGenreCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        String name = request.getStringParameter(ParameterDestination.NAME.getParameter());
        try {
            genreService.createGenre(name);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return() -> ADD_GENRE_PANEL;
    }
}
