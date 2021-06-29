package controller.command.role.user;

import controller.command.*;
import dto.AccountDto;
import entity.Account;
import exception.CommandException;
import exception.ServiceException;
import service.AccountService;
import service.Service;
import service.factory.ServiceInstance;

import static controller.command.ControllerDestination.INFO;

public class OrderBookCommand implements Command {

    private static OrderBookCommand INSTANCE;
    private AccountService accountService;

    private OrderBookCommand() {
        accountService = (AccountService) Service.of(Account.class);
    }

    public static OrderBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderBookCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        try {
            AccountDto account = (AccountDto) request.getSessionAttribute(ParameterDestination.ACCOUNT.getParameter());
            Integer bookId = request.getIntParameter(ParameterDestination.BOOK_ID.getParameter());
            Integer accountId = account.getId();
            request.setAttribute(ParameterDestination.INFO.getParameter(), accountService.orderBook(accountId, bookId));
            account = accountService.getOne(accountId);
            request.setSessionAttribute(ParameterDestination.ACCOUNT.getParameter(), account);
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage());
        }
        return() -> INFO;
    }
}
