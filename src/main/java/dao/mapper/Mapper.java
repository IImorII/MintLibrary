package dao.mapper;

import entity.BaseEntity;
import exception.MapperException;

import java.sql.ResultSet;

public interface Mapper <T extends BaseEntity> {
    T toEntity(ResultSet rs) throws MapperException;
}
