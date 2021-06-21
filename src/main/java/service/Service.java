package service;

import entity.BaseEntity;
import service.factory.ServiceInstance;

import java.util.List;

public interface Service<T extends BaseEntity, K> {
    List<K> getAll();

    static Service of(Class<? extends BaseEntity> entity) {
        return ServiceInstance.of(entity.getSimpleName());
    }
}
