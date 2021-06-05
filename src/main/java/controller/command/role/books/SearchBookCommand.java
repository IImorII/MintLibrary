package controller.command.role.books;

import controller.command.*;
import dto.BookDto;
import exception.CommandException;
import service.BookService;
import service.impl.BookServiceImpl;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

import static controller.command.CommandInstance.SWITCH_PAGE;

public class SearchBookCommand implements Command {

    private static SearchBookCommand INSTANCE;
    private BookService bookService;

    private SearchBookCommand() {
        bookService = BookServiceImpl.getInstance();
    }

    public static SearchBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SearchBookCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Object searchParameter = request.getParameter(ParameterDestination.SEARCH.getParameter());
        String searchName;
        if (searchParameter == null) {
            if (session != null) {
                searchParameter = session.getAttribute(ParameterDestination.SEARCH.getParameter());
            }
            if (searchParameter == null) {
                searchName = "";
            } else {
                searchName = searchParameter.toString();
            }
        } else {
            searchName = searchParameter.toString();
        }
        List<BookDto> books = bookService.getAll();
        books = books.stream()
                .filter(book -> book.getName().contains(searchName))
                .collect(Collectors.toList());
        request.setAttribute(ParameterDestination.BOOKS_LIST_FULL.getParameter(), books);
        request.setSessionAttribute(ParameterDestination.SEARCH.getParameter(), searchName);
        request.setAttribute(ParameterDestination.CURRENT_PAGE.getParameter(), 1);
        return Command.of(SWITCH_PAGE).execute(request);
    }
}
