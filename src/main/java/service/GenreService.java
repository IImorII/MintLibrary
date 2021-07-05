package service;

import dto.GenreDto;
import entity.Genre;
import exception.ServiceException;

import java.util.List;

public interface GenreService extends Service<Genre, GenreDto> {
    void createGenre(String name) throws ServiceException;
    void deleteGenre(Integer genreId) throws ServiceException;
    void updateGenre(Integer genreId, String name) throws ServiceException;
}
