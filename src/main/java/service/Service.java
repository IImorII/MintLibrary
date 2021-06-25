package service;

import entity.BaseEntity;
import exception.ServiceException;
import service.factory.ServiceInstance;

import java.util.List;

public interface Service<T extends BaseEntity, K> {
    List<K> getAll() throws ServiceException;

    static Service of(Class<? extends BaseEntity> entity) {
        return ServiceInstance.of(entity.getSimpleName());
    }
}
