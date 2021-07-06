package service;

import dto.AuthorDto;
import entity.Author;
import exception.ServiceException;

public interface AuthorService extends Service<Author, AuthorDto> {
    void createAuthor(String name, Integer birth, String[] languagesId) throws ServiceException;
    void deleteAuthor(Integer authorId) throws ServiceException;
    void updateAuthor(Integer authorId, String name, Integer birth) throws ServiceException;
}
