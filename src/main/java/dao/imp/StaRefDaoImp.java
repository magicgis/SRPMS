package dao.imp;

import entity.StaRef;
import dao.StaRefDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaRefDaoImp extends BaseDaoImp<StaRef> implements StaRefDao {
    @Override
    public List<StaRef> getByTypeAndRole(String id, String type, Integer role) {
        String hql;
        if (type == null) {
            if (role == null) {
                hql = "from StaRef where staff = '" + id + "'";
            }
            else {
                hql = "from StaRef where staff = '" + id + "' and role =" + role;
            }
        }
        else {
            if (role == null) {
                hql = "from StaRef where staff = '" + id + "' and type ='" + type + "'";
            }
            else {
                hql = "from StaRef where staff = '" + id + "' and type = '" + type + "' and staff = '" + id + "'";
            }
        }
        return getCurrentSession().createQuery(hql).list();
    }

    @Override
    public Boolean removeRelation(String entity, String type) {
        String hql = "delete StaRef where entity = '" + entity + "' and type = '" + type + "'";
        try {
            getCurrentSession().createQuery(hql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}