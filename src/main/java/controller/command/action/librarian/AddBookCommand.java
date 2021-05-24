package controller.command.action.librarian;

import controller.command.Command;
import controller.command.CommandRequest;
import controller.command.CommandResponse;
import controller.command.ParameterDestination;
import controller.command.action.user.OrderBookCommand;
import exception.CommandException;
import exception.ConnectionException;
import exception.DaoException;
import service.impl.AccountServiceImpl;
import service.impl.BookServiceImpl;
import util.ImageUploadUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static controller.command.ControllerDestination.ADD_BOOK_PANEL;

public class AddBookCommand implements Command {

    private static AddBookCommand INSTANCE;

    private AddBookCommand() {

    }

    public static AddBookCommand getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AddBookCommand();
        }
        return INSTANCE;
    }
    @Override
    public CommandResponse execute(CommandRequest request) throws CommandException {
        String name = request.getStringParameter(ParameterDestination.NAME.getParameter());
        String photoUrl = request.getStringParameter(ParameterDestination.PHOTO_URL.getParameter());
        String description = request.getStringParameter(ParameterDestination.DESCRIPTION.getParameter());
        Integer year = request.getIntParameter(ParameterDestination.YEAR.getParameter());
        Integer count = request.getIntParameter(ParameterDestination.COUNT.getParameter());
        String author = request.getStringParameter(ParameterDestination.AUTHOR.getParameter());
        String genre = request.getStringParameter(ParameterDestination.GENRE.getParameter());
        String language = request.getStringParameter(ParameterDestination.LANGUAGE.getParameter());
        try {
            BookServiceImpl.getInstance().createBook(name, description, author, genre, language, photoUrl, count, year);
        } catch (DaoException | ConnectionException ex) {
            throw new CommandException(ex.getMessage());
        }
        return() -> ADD_BOOK_PANEL;
    }
}
