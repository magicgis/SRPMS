package service.security.imp;

import dao.security.PermissionDao;
import dao.security.RoleDao;
import dao.security.UrlDao;
import entity.security.Permission;
import entity.security.Role;
import entity.security.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.security.SecurityService;
import util.CrunchifyInMemoryCache;

import java.io.Serializable;
import java.util.List;

import static util.Args.PermissionCache;

/**
 * Created by guofan on 2015/6/29.
 */
@Service
public class SecurityServiceImp implements SecurityService {

    @Autowired
    PermissionDao permissionDao;
    @Autowired
    UrlDao urlDao;
    @Autowired
    RoleDao roleDao;

    public void loadIntoCache() {
        List<Permission> temp = permissionDao.getAll();
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


    public Serializable addUrl(String path, String type, String define) {
        if (urlDao.getById(path + "-" + type) == null) {
            Url url = new Url();
            url.setId(path + "-" + type);
            url.setUrl(path);
            url.setType(type);
            url.setDefine(define);
            return urlDao.add(url);
        } else {
            return path + "-" + type;
        }
    }

    public boolean deleteByPathAndType(String path, String type) {
        Url url = urlDao.getById(path + "-" + type);
        if (url != null) {
            return urlDao.delete(url);
        } else {
            return false;
        }
    }

    public boolean update(String path, String type, String define) {
        Url url = urlDao.getById(path + "-" + type);
        if (url != null) {
            url.setDefine(define);
            return urlDao.update(url);
        } else {
            return false;
        }
    }


    public boolean addRole(String role, String define) {
        Role roleObj = new Role();
        roleObj.setRole(role);
        roleObj.setDefine(define);
        return roleDao.save(roleObj);
    }

    public boolean updateRole(String role, String define) {
        Role roleObj = roleDao.getById(role);
        if (roleObj == null) {
            return false;
        } else {
            roleObj.setDefine(define);
            return roleDao.update(roleObj);
        }
    }

    public boolean removeRole(String role) {
        Role roleObj = roleDao.getById(role);
        if (roleObj == null) {
            return true;
        } else {
            return roleDao.delete(roleObj);
        }
    }

}
