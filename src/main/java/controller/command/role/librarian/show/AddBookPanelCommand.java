package controller.command.role.librarian.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.AuthorDto;
import dto.GenreDto;
import dto.LanguageDto;
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

import static controller.command.ControllerDestination.ADD_BOOK_PANEL;

public class AddBookPanelCommand implements Command {

    private static AddBookPanelCommand INSTANCE;

    private GenreService genreService;
    private LanguageService languageService;
    private AuthorService authorService;

    private AddBookPanelCommand() {
        genreService = (GenreService) Service.of(Genre.class);
        languageService = (LanguageService) Service.of(Language.class);
        authorService = (AuthorService) Service.of(Author.class);
    }

    public static AddBookPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddBookPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            List<GenreDto> genres = genreService.getAll();
            List<AuthorDto> authors = authorService.getAll();
            List<LanguageDto> languages = languageService.getAll();
            request.setAttribute(ParameterDestination.GENRES_LIST.getParameter(), genres);
            request.setAttribute(ParameterDestination.AUTHORS_LIST.getParameter(), authors);
            request.setAttribute(ParameterDestination.LANGUAGES_LIST.getParameter(), languages);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> ADD_BOOK_PANEL;
    }
}
