package controller.command.role.librarian.show;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import dto.BookDto;
import exception.CommandException;
import service.AccountService;
import service.BookService;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static controller.command.ControllerDestination.RELEASE_ORDER_PANEL;


public class ReleaseOrderPanelCommand implements Command {
    private static ReleaseOrderPanelCommand INSTANCE;

    private BookService bookService;
    private AccountService accountService;

    private ReleaseOrderPanelCommand() {
        bookService = BookServiceImpl.getInstance();
        accountService = AccountServiceImpl.getInstance();
    }

    public static ReleaseOrderPanelCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReleaseOrderPanelCommand();
        }
        return INSTANCE;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        Object userId = request.getParameter(ParameterDestination.USER_ID.getParameter());
        List<BookDto> books = new ArrayList<>();
        if (userId != null) {
            books = bookService.getConfirmedBooks(Integer.parseInt(userId.toString()));
            request.setAttribute(ParameterDestination.USER.getParameter(), accountService.getOne(Integer.parseInt(userId.toString())));
        }
        request.setAttribute(ParameterDestination.BOOKS_LIST.getParameter(), books);
        return () -> RELEASE_ORDER_PANEL;
    }
}
