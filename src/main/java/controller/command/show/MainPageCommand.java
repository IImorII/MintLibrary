package controller.command.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import entity.dto.BookDto;
import service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static controller.command.ControllerDestination.MAIN;

public class MainPageCommand implements Command {

    private List<BookDto> books;

    public MainPageCommand() {
        books = BookServiceImpl.getInstance().getAll();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Integer booksPerPage = 8;
        List<BookDto> booksToShow = new ArrayList<>(books);
        Object pageParameter = request.getParameter(ParameterDestination.PAGE.getParameter());
        Object searchParameter = request.getParameter(ParameterDestination.SEARCH.getParameter());
        Integer currentPage;
        String searchName;
        if (searchParameter == null) {
            searchParameter = request.getSessionAttribute(ParameterDestination.SEARCH.getParameter());
            if (searchParameter == null) {
                searchName = "";
            } else {
                searchName = searchParameter.toString();
            }
        } else {
            searchName = searchParameter.toString();
        }
        booksToShow = booksToShow.stream()
                .filter(book -> book.getName().contains(searchName))
                .collect(Collectors.toList());
        Integer lastPage = (int) Math.ceil((double) booksToShow.size() / booksPerPage);
        if (pageParameter == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(pageParameter.toString());
        }
        Integer startIndex = (currentPage - 1) * booksPerPage;
        Integer endIndex = currentPage * booksPerPage;
        if (startIndex + booksPerPage >= booksToShow.size()) {
            endIndex = booksToShow.size();
        }
        booksToShow = booksToShow.subList(startIndex, endIndex);
        request.setAttribute(ParameterDestination.BOOKS_LIST.getParameter(), booksToShow);
        request.setAttribute(ParameterDestination.PAGE.getParameter(), currentPage);
        request.setAttribute(ParameterDestination.LAST_PAGE.getParameter(), lastPage);
        request.setSessionAttribute(ParameterDestination.SEARCH.getParameter(), searchName);
        return () -> MAIN;
    }
}
