package controller.command.role.librarian;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.Author;
import entity.Book;
import entity.Genre;
import entity.Language;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import exception.ServiceException;
import service.*;
import service.factory.ServiceInstance;

import static controller.command.ControllerDestination.*;

public class AddLanguageCommand implements Command {

    private static AddLanguageCommand INSTANCE;
    private LanguageService languageService;

    private AddLanguageCommand() {
        languageService = (LanguageService) Service.of(Language.class);
    }

    public static AddLanguageCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddLanguageCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        String name = request.getStringParameter(ParameterDestination.NAME.getParameter());
        try {
            languageService.createLanguage(name);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return() -> ADD_LANGUAGE_PANEL;
    }
}
