package controller.command.role.librarian.add;

import controller.command.*;
import entity.Language;
import exception.CommandException;
import exception.ServiceException;
import service.LanguageService;
import service.Service;

import static controller.command.ControllerDestination.*;

public class AddLanguageCommand implements Command {

    private static AddLanguageCommand INSTANCE;
    private final LanguageService languageService;

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
        return Command.of(CommandInstance.ADD_LANGUAGE_PANEL).execute(request);
    }
}
