package controller.command.action.books;

import controller.command.*;
import controller.command.show.MainPageCommand;
import dto.BookDto;
import exception.CommandException;

import java.util.List;

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

    private Integer booksPerPage = 8;

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        List<BookDto> books;
        Object booksParameter = request.getAttribute(ParameterDestination.BOOKS_LIST_FULL.getParameter());
        if (booksParameter == null) {
            return Command.of(CommandInstance.SEARCH_BOOK.name()).execute(request);
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
        return Command.of(CommandInstance.MAIN.name()).execute(request);
    }
}
