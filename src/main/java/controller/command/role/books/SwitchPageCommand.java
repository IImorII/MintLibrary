package controller.command.role.books;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.BookDto;
import exception.CommandException;

import java.util.List;

import static controller.command.CommandInstance.MAIN;
import static controller.command.CommandInstance.SEARCH_BOOK;

public class SwitchPageCommand implements Command {

    private static SwitchPageCommand INSTANCE;

    private SwitchPageCommand() {

    }

    public static SwitchPageCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SwitchPageCommand();
        }
        return INSTANCE;
    }

    private final Integer booksPerPage = 6;

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        List<BookDto> books;
        Object booksParameter = request.getSessionAttribute(ParameterDestination.BOOKS_LIST_FULL.getParameter());
        if (booksParameter == null) {
            return Command.of(SEARCH_BOOK).execute(request);
        } else {
            books = (List<BookDto>) booksParameter;
        }
        Object pageParameter = request.getParameter(ParameterDestination.CURRENT_PAGE.getParameter());
        Integer currentPage;
        Integer lastPage = (int) Math.ceil((double) books.size() / booksPerPage);
        if (pageParameter == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(pageParameter.toString());
        }
        Integer startIndex = (currentPage - 1) * booksPerPage;
        Integer endIndex = currentPage * booksPerPage;
        if (startIndex + booksPerPage >= books.size()) {
            endIndex = books.size();
        }
        request.setAttribute(ParameterDestination.BOOKS_LIST.getParameter(), books.subList(startIndex, endIndex));
        request.setAttribute(ParameterDestination.CURRENT_PAGE.getParameter(), currentPage);
        request.setAttribute(ParameterDestination.LAST_PAGE.getParameter(), lastPage);
        return Command.of(MAIN).execute(request);
    }
}
