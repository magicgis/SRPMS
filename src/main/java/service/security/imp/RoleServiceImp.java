package service.security.imp;

import dao.security.RoleDao;
import entity.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.imp.BaseServiceImp;
import service.security.RoleService;

import java.util.List;

/**
 * Created by guofan on 2015/5/9.
 */
@Service
public class RoleServiceImp extends BaseServiceImp<Role> implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public boolean addRole(String role, String define) {
        Role roleObj = new Role();
        roleObj.setRole(role);
        roleObj.setDefine(define);
        return roleDao.save(roleObj);
    }

    @Override
    public boolean updateRole(String role, String define) {
        Role roleObj = roleDao.getById(role);
        if (roleObj == null) {
            return false;
        } else {
            roleObj.setDefine(define);
            return roleDao.update(roleObj);
        }
    }

    @Override
    public boolean removeRole(String role) {
        Role roleObj = roleDao.getById(role);
        if (roleObj == null) {
            return true;
        } else {
            return roleDao.delete(roleObj);
        }
    }

    @Override
    public List<Role> search(String keyword, String sort, String order) {
        return null;
    }
}
