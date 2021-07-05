package controller.command.role.librarian.add;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.Genre;
import exception.CommandException;
import exception.ServiceException;
import service.GenreService;
import service.Service;

import static controller.command.ControllerDestination.ADD_GENRE_PANEL;

public class AddGenreCommand implements Command {

    private static AddGenreCommand INSTANCE;
    private final GenreService genreService;

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
