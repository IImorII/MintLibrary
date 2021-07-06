package service.impl;

import dao.Dao;
import dao.RoleDao;
import dto.RoleDto;
import entity.Role;
import exception.MapperException;
import mapper.Mapper;
import mapper.RoleMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import cache.EntityCache;
import service.RoleService;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    private static final String ADMIN = "Admin";

    private static final Logger log = LogManager.getLogger(RoleServiceImpl.class);
    private static RoleServiceImpl INSTANCE;
    private final RoleMapper roleMapper;
    private final EntityCache cache;

    private RoleServiceImpl() {
        RoleDao roleDao = (RoleDao) Dao.of(Role.class);
        roleMapper = (RoleMapper) Mapper.of(Role.class);
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

    @Override
    public List<RoleDto> getAllWithoutAdmin() {
        List<RoleDto> dtoRoles = new ArrayList<>();
        try {
            List<Role> entityRoles = (List<Role>) cache.retrieveCollection(Role.class);
            for (Role r : entityRoles) {
                if (r.getName().equalsIgnoreCase(ADMIN)) {
                    continue;
                }
                dtoRoles.add(roleMapper.toDto(r));
            }
        } catch (MapperException ex) {
            log.error(ex.getMessage());
        }
        return dtoRoles;
    }
}
