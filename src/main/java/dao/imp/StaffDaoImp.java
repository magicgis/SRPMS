package dao.imp;

import entity.Staff;
import dao.StaffDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StaffDaoImp extends BaseDaoImp<Staff> implements StaffDao {
    @Override
    public List<Staff> findByArrayFuz(List<String> keys, String keyword, String sort, String order) {
        String hql = "from Staff where LENGTH(id) >= 4  ";
        String where = "";
        if (keyword != null && !keyword.equals("")) {
            for (String s : keys) {
                where = where + s + " LIKE " + "'%" + keyword + "%' OR ";
            }
            hql += " and " + where.substring(0,where.length()-3);
        }
        if (sort != null) {
            hql = hql + " order by " + sort + " " + order;
        }
        return this.getCurrentSession().createQuery(hql).list();
    }
}