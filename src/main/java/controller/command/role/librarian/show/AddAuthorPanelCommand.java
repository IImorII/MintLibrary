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
import service.factory.ServiceInstance;

import java.util.List;

import static controller.command.ControllerDestination.ADD_AUTHOR_PANEL;
import static controller.command.ControllerDestination.ADD_BOOK_PANEL;

public class AddAuthorPanelCommand implements Command {

    private static AddAuthorPanelCommand INSTANCE;

    private LanguageService languageService;
    private AuthorService authorService;

    private AddAuthorPanelCommand() {
        languageService = (LanguageService) Service.of(Language.class);
        authorService = (AuthorService) Service.of(Author.class);
    }

    public static AddAuthorPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddAuthorPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            List<LanguageDto> languages = languageService.getAll();
            request.setAttribute(ParameterDestination.LANGUAGES_LIST.getParameter(), languages);
            List<AuthorDto> authors = authorService.getAll();
            request.setAttribute(ParameterDestination.AUTHORS_LIST.getParameter(), authors);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return () -> ADD_AUTHOR_PANEL;
    }
}
