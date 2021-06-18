package service.impl;

import dao.RoleDao;
import dao.factory.ProxyDaoFactory;
import dto.RoleDto;
import entity.Role;
import exception.MapperException;
import mapper.RoleMapper;
import mapper.factory.MapperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cache.EntityCache;
import service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static Logger log = LogManager.getLogger(RoleServiceImpl.class);
    private static RoleServiceImpl INSTANCE;
    private RoleDao roleDao;
    private RoleMapper roleMapper;
    private EntityCache cache;

    private RoleServiceImpl() {
        roleDao = (RoleDao) ProxyDaoFactory.get(Role.class);
        roleMapper = (RoleMapper) MapperFactory.get(Role.class);
        cache = EntityCache.getInstance();
    }

    public static RoleServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<RoleDto> getAll() {
        List<RoleDto> dtoRoles = new ArrayList<>();
        try {
            List<Role> entityRoles = (List<Role>) cache.retrieveCollection(Role.class);
            for (Role r : entityRoles) {
                dtoRoles.add(roleMapper.toDto(r));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoRoles;
    }

}
