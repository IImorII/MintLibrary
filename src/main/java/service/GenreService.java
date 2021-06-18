package service;

import dto.GenreDto;

import java.util.List;

public interface GenreService {
    void createGenre(String name);
    List<GenreDto> getAll();
}
