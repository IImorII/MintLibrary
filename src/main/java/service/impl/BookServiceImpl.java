package service.impl;

import mapper.BookMapper;
import entity.Book;
import dto.BookDto;
import exception.MapperException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.EntityRepository;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl {
    private static Logger log = LogManager.getLogger(BookServiceImpl.class);
    private static BookServiceImpl INSTANCE;

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookServiceImpl();
        }
        return INSTANCE;
    }

    public List<BookDto> getAll() {
        List<BookDto> dtoBooks = new ArrayList<>();
        try {
            List<Book> entityBooks = (List<Book>) EntityRepository.getInstance().retrieveCollection(Book.class);
            for (Book e : entityBooks) {
                dtoBooks.add(BookMapper.getInstance().toDto(e));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoBooks;
    }
}
