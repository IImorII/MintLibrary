package mapper;

import dto.LanguageDto;
import dto.RoleDto;
import entity.Language;
import entity.Role;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageMapper implements Mapper<Language, LanguageDto> {

    private static LanguageMapper INSTANCE;

    private LanguageMapper() {
    }

    public static LanguageMapper getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new LanguageMapper();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public Language toEntity(ResultSet rs) throws MapperException {
        try {
            final Integer id = rs.getInt("id");
            final String name = rs.getString("name");
            return new Language(id, name);
        } catch (SQLException ex) {
            throw new MapperException(ex.getMessage());
        }
    }

    @Override
    public LanguageDto toDto(Language entity) throws MapperException {
        final Integer id = entity.getId();
        final String name = entity.getName();
        return new LanguageDto(id, name);
    }
}
