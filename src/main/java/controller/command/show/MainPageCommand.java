package controller.command.show;

import controller.command.*;
import dto.AuthorDto;
import dto.GenreDto;
import entity.Author;
import entity.Genre;
import entity.Language;
import exception.CommandException;
import exception.ServiceException;
import service.AuthorService;
import service.GenreService;
import service.LanguageService;
import service.Service;

import java.util.List;

import static controller.command.ControllerDestination.MAIN;

public class MainPageCommand implements Command {

    private static MainPageCommand INSTANCE;

    private GenreService genreService;
    private AuthorService authorService;

    private MainPageCommand() {
        genreService = (GenreService) Service.of(Genre.class);
        authorService = (AuthorService) Service.of(Author.class);
    }

    public static MainPageCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainPageCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Object currentPage = request.getAttribute(ParameterDestination.CURRENT_PAGE.getParameter());
            List<GenreDto> genres = genreService.getAll();
            List<AuthorDto> authors = authorService.getAll();
            request.setAttribute(ParameterDestination.GENRES_LIST.getParameter(), genres);
            request.setAttribute(ParameterDestination.AUTHORS_LIST.getParameter(), authors);
            if (currentPage == null) {
                return Command.of(CommandInstance.SWITCH_PAGE.name()).execute(request);
            }
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> MAIN;
    }
}
