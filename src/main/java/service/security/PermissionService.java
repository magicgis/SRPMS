package service.security;

import entity.security.Permission;
import entity.security.Role;
import entity.security.Url;
import service.BaseService;

/**
 * Created by guofan on 2015/5/8.
 */
public interface PermissionService extends BaseService<Permission> {
    void loadIntoCache();
}
