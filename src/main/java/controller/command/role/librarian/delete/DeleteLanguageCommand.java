package controller.command.role.librarian.delete;

import controller.command.*;
import entity.Genre;
import entity.Language;
import exception.CommandException;
import exception.ServiceException;
import service.GenreService;
import service.LanguageService;
import service.Service;

public class DeleteLanguageCommand implements Command {

    private static DeleteLanguageCommand INSTANCE;
    private final LanguageService languageService;

    private DeleteLanguageCommand() {
        languageService = (LanguageService) Service.of(Language.class);
    }

    public static DeleteLanguageCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeleteLanguageCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer languageId = request.getIntParameter(ParameterDestination.LANGUAGE_ID.getParameter());
            languageService.deleteLanguage(languageId);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.MAIN).execute(request);
    }
}
