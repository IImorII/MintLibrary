package service;

import dto.LanguageDto;
import dto.RoleDto;

import java.util.List;

public interface LanguageService {
    void createLanguage(String name);
    List<LanguageDto> getAll();
}
