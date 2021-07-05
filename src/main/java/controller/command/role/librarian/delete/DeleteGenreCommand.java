package controller.command.role.librarian.delete;

import controller.command.*;
import entity.Book;
import entity.Genre;
import exception.CommandException;
import exception.ServiceException;
import service.BookService;
import service.GenreService;
import service.Service;

public class DeleteGenreCommand implements Command {

    private static DeleteGenreCommand INSTANCE;
    private final GenreService genreService;

    private DeleteGenreCommand() {
        genreService = (GenreService) Service.of(Genre.class);
    }

    public static DeleteGenreCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeleteGenreCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer genreId = request.getIntParameter(ParameterDestination.GENRE_ID.getParameter());
            genreService.deleteGenre(genreId);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.MAIN).execute(request);
    }
}
