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
import service.AuthorService;
import service.GenreService;
import service.LanguageService;
import service.Service;
import service.factory.ServiceInstance;

import java.util.List;

import static controller.command.ControllerDestination.ADD_BOOK_PANEL;
import static controller.command.ControllerDestination.ADD_LANGUAGE_PANEL;

public class AddLanguagePanelCommand implements Command {

    private static AddLanguagePanelCommand INSTANCE;

    private AddLanguagePanelCommand() {
    }

    public static AddLanguagePanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddLanguagePanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        return () -> ADD_LANGUAGE_PANEL;
    }
}
