package service;

import dto.AuthorDto;
import entity.Language;

import java.util.List;

public interface AuthorService {
    void createAuthor(String name, Integer birth, String[] languagesId);
    List<AuthorDto> getAll();
}
