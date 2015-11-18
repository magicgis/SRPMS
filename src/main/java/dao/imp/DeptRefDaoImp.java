package dao.imp;

import dao.DeptRefDao;
import entity.DeptRef;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by guofan on 2015/11/18.
 */
@Repository
public class DeptRefDaoImp extends BaseDaoImp<DeptRef> implements DeptRefDao {
    @Override
    public List<DeptRef> getByTypeAndRole(String id, String type, Integer role) {
        String hql;
        if (type == null) {
            if (role == null) {
                hql = "from DeptRef where dept = '" + id + "'";
            }
            else {
                hql = "from DeptRef where dept = '" + id + "' and role =" + role;
            }
        }
        else {
            if (role == null) {
                hql = "from DeptRef where dept = '" + id + "' and type ='" + type + "'";
            }
            else {
                hql = "from DeptRef where dept = '" + id + "' and type = '" + type + "' and staff = '" + id + "'";
            }
        }
        return getCurrentSession().createQuery(hql).list();
    }

    @Override
    public List<DeptRef> getByType(String id, String type) {
        return getByTypeAndRole(id, type, null);
    }

    @Override
    public List<DeptRef> get(String id) {
        return getByTypeAndRole(id, null, null);
    }

    @Override
    public void removeRelation(String entity, String type) {
        String hql = "delete DeptRef where entityId = '" + entity + "' and type = '" + type + "'";
        try {
            getCurrentSession().createQuery(hql);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
