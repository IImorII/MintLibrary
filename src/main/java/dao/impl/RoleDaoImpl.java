package dao.impl;

import dao.AbstractBaseDao;
import dao.RoleDao;
import dto.RoleDto;
import mapper.Mapper;
import mapper.RoleMapper;
import entity.Role;
import mapper.factory.MapperFactory;

public class RoleDaoImpl extends AbstractBaseDao<Role> implements RoleDao {

    private static RoleDaoImpl INSTANCE;

    private RoleDaoImpl() {
        super("role");
    }

    public static RoleDaoImpl getInstance() {
        try {
            LOCK.lock();
            if (INSTANCE == null) {
                INSTANCE = new RoleDaoImpl();
            }
            return INSTANCE;
        } finally {
            LOCK.unlock();
        }
    }

    @Override
    public Mapper<Role, RoleDto> getMapper() {
        return MapperFactory.get(Role.class);
    }
}
