package service;

import dto.LanguageDto;
import entity.Language;
import exception.ServiceException;

public interface LanguageService extends Service<Language, LanguageDto> {
    void createLanguage(String name) throws ServiceException;
    void deleteLanguage(Integer languageId) throws ServiceException;
    void updateLanguage(Integer languageId, String name) throws ServiceException;
}
