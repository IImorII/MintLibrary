package controller.command.role.librarian.delete;

import controller.command.Command;
import controller.command.CommandInstance;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.Author;
import exception.CommandException;
import exception.ServiceException;
import service.AuthorService;
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
