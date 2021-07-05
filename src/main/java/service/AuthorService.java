package service;

import dto.AuthorDto;
import entity.Author;
import entity.Language;
import exception.ServiceException;

import java.util.List;

public interface AuthorService extends Service<Author, AuthorDto> {
    void createAuthor(String name, Integer birth, String[] languagesId) throws ServiceException;
    void deleteAuthor(Integer authorId) throws ServiceException;
    void updateAuthor(Integer authorId, String name, Integer birth) throws ServiceException;
}
