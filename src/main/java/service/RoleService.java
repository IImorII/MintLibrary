package service;

import dto.RoleDto;
import entity.Role;

import java.util.List;

public interface RoleService extends Service<Role, RoleDto> {
    List<RoleDto> getAllWithoutAdmin();
}
