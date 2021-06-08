package dao.impl;

import dao.AbstractBaseDao;
import dao.RoleDao;
import mapper.Mapper;
import mapper.RoleMapper;
import entity.Role;

public class RoleDaoImpl extends AbstractBaseDao<Role> implements RoleDao {

    private static RoleDaoImpl INSTANCE;

    private RoleDaoImpl() {
        super("role");
    }

    protected static RoleDaoImpl getInstance() {
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
    public Mapper<Role> getMapper() {
        return RoleMapper.getInstance();
    }
}
