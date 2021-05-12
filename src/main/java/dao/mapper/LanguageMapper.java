package dao.mapper;

import entity.Language;
import exception.MapperException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageMapper implements Mapper<Language> {
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
}
