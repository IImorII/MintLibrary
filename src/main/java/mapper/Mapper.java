package mapper;

import entity.BaseEntity;
import exception.MapperException;

import java.sql.ResultSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public interface Mapper<T extends BaseEntity, K> {
    Lock LOCK = new ReentrantLock();
    T toEntity(ResultSet rs) throws MapperException;
    K toDto(T entity) throws MapperException;
}
