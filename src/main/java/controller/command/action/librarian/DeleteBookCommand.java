package controller.command.action.librarian;

import controller.command.*;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import service.impl.BookServiceImpl;

import static controller.command.ControllerDestination.MAIN;

public class DeleteBookCommand implements Command {

    private static DeleteBookCommand INSTANCE;

    private DeleteBookCommand() {

    }

    public static DeleteBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DeleteBookCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            BookServiceImpl.getInstance().deleteBook(request.getIntParameter(ParameterDestination.BOOK_ID.getParameter()));
        } catch (DaoException | ConnectionException ex) {
            throw new CommandException(ex.getMessage());
        }
        return Command.of(CommandInstance.MAIN.name()).execute(request);
    }
}
