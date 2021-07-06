package dao.impl;

import dao.AbstractDao;
import dao.RoleDao;
import dto.RoleDto;
import mapper.Mapper;
import entity.Role;


public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {

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
        return Mapper.of(Role.class);
    }
}
