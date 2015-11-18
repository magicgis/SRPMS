package service.imp;

import dao.BaseDao;
import dao.DeptRefDao;
import entity.DeptRef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.DeptRefService;
import util.StaticFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static util.Trans.changeTypeName;

/**
 * Created by guofan on 2015/11/18.
 */
@Service
public class DeptRefServiceImp extends BaseServiceImp<DeptRef> implements DeptRefService {
    @Autowired
    DeptRefDao deptRefDao;

    private static final Logger log = LogManager.getLogger(DeptRefServiceImp.class);

    @Override
    public List<DeptRef> search(String keyword, String sort, String order) {
        return new ArrayList<>();
    }

    @Override
    public Object getEntity(String id) {
        DeptRef deptRef = deptRefDao.getById(id);
        if (deptRef != null) {
            return getEntity(deptRef);
        }
        else {
            return null;
        }
    }

    @Override
    public Object getEntity(DeptRef deptRef) {
        Object ans = new Object();
        String type = deptRef.getType();
        try {
            Class x = Class.forName(deptRefDao.getClass().getPackage() + "." + changeTypeName(type) + "Dao");
            Object dao = StaticFactory.getBean(x);
            Method getById = x.getMethod("getById");
            ans = getById.invoke(dao, deptRef.getEntityId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("哪儿好像出错了");
        }
        return ans;
    }

    @Override
    public List<Object> getEntity(String id, String type) {
        List<DeptRef> res = deptRefDao.getByType(id, type);
        List<Object> ans = new LinkedList<>();

        Class x = null;
        try {
            StringBuilder meType = new StringBuilder(type);
            meType.setCharAt(0, Character.toUpperCase(meType.charAt(0)));
            x = Class.forName(DeptRefDao.class.getPackage().getName() + "." + meType.toString() + "Dao");
            BaseDao dao = (BaseDao) StaticFactory.getBean(x);

            for (DeptRef re : res) {
                ans.add(dao.getById(re.getEntityId()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return ans;
    }


}
