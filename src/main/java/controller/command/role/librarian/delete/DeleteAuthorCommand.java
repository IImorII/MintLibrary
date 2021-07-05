package controller.command.role.librarian.delete;

import controller.command.*;
import entity.Author;
import entity.Genre;
import exception.CommandException;
import exception.ServiceException;
import service.AuthorService;
import service.GenreService;
import service.Service;

public class DeleteAuthorCommand implements Command {

    private static DeleteAuthorCommand INSTANCE;
    private final AuthorService authorService;

    private DeleteAuthorCommand() {
        authorService = (AuthorService) Service.of(Author.class);
    }

    public static DeleteAuthorCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeleteAuthorCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            Integer authorId = request.getIntParameter(ParameterDestination.AUTHOR_ID.getParameter());
            authorService.deleteAuthor(authorId);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.MAIN).execute(request);
    }
}
