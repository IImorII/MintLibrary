package dao.impl;

import dao.AbstractBaseDao;
import dao.BaseDao;
import dao.RoleDao;
import dao.mapper.Mapper;
import dao.mapper.RoleMapper;
import entity.Role;
import exception.ConnectionException;
import exception.DaoException;

import java.util.*;

public class RoleDaoImpl extends AbstractBaseDao<Role> implements RoleDao {

    private static RoleDaoImpl INSTANCE;

    private RoleDaoImpl() {
        super("role");
    }

    public static RoleDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public Mapper<Role> getMapper() {
        return new RoleMapper();
    }
}
