package service.security.imp;

import dao.security.PermissionDao;
import dao.security.RoleDao;
import dao.security.UrlDao;
import entity.security.Permission;
import entity.security.Role;
import entity.security.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.imp.BaseServiceImp;
import service.security.PermissionService;
import util.CrunchifyInMemoryCache;

import java.util.List;

import static util.Args.PermissionCache;

/**
 * Created by guofan on 2015/5/9.
 */
@Service
public class PermissionServiceImp extends BaseServiceImp<Permission> implements PermissionService {

    @Autowired
    PermissionDao permissionDao;
    @Autowired
    UrlDao urlDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Permission> search(String keyword, String sort, String order) {
        return null;
    }

    @Override
    public void loadIntoCache() {
        List<Permission> temp = getAll();
        if (PermissionCache == null) {
            PermissionCache = new CrunchifyInMemoryCache<>(0, 0, 3000);
        } else {
            PermissionCache.empty();
        }
        for (Permission permission : temp) {
            Role role = permission.getRole();
            Url url = permission.getUrl();
            PermissionCache.put(role.getRole() + "-" + url.getUrl() + "-" + url.getType(), permission.getIsPermit());
        }
    }

    public boolean setPermit(String roleStr, String urlId, Boolean permit) {
        Role role = roleDao.getById(roleStr);
        Url url = urlDao.getById(urlId);
        Permission permission = new Permission();
        permission.setRole(role);
        permission.setUrl(url);
        permission.setIsPermit(permit);
        return permissionDao.save(permission);
    }

}
