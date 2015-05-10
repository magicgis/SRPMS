package service.security;

import entity.security.Role;
import service.BaseService;

/**
 * Created by guofan on 2015/5/8.
 */
public interface RoleService extends BaseService<Role> {
    boolean addRole(String role, String define);

    boolean updateRole(String role, String define);

    boolean removeRole(String role);
}
