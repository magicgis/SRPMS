package dao.security.imp;

import dao.imp.BaseDaoImp;
import dao.security.RoleDao;
import entity.security.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by guofan on 2015/5/8.
 */
@Repository
public class RoleDaoImp extends BaseDaoImp<Role> implements RoleDao {
}
