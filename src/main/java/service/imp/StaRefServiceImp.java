package service.imp;

import dao.StaRefDao;
import entity.StaRef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StaRefService;
import util.StaticFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class StaRefServiceImp extends BaseServiceImp<StaRef> implements StaRefService {

    @Autowired
    StaRefDao staRefDao;

    private static final Logger log = LogManager.getLogger(StaRefServiceImp.class);

    @Override
    public List<StaRef> search(String keyword, String sort, String order) {
        return null;
    }

    @Override
    public List<StaRef> getUserList(Object entity) {
        List<StaRef> ans = new LinkedList<>();
        Class type = entity.getClass();
        try {
            Method getId = type.getMethod("getId");
            String id = (String) getId.invoke(entity);
            HashMap<String, Object> query = new HashMap();
            query.put("entityId", id);
            query.put("type", type.getSimpleName());
            ans = staRefDao.findByMapAcc(query);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("传入的实体可能有错误:" + entity);
        }
        return ans;
    }

    @Override
    public Object getEntity(String id) {
        StaRef staRef = staRefDao.getById(id);
        if (staRef == null) {
            return null;
        }
        return getEntity(staRef);
    }

    @Override
    public Object getEntity(StaRef staRef) {
        Object ans = new Object();
        String type = staRef.getType();
        try {
            Class x = Class.forName(staRefDao.getClass().getPackage() + "." + type + "Dao");
            Object dao = StaticFactory.getBean(x);
            Method getById = x.getMethod("getById");
            ans = getById.invoke(dao, staRef.getEntityId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("哪儿好像出错了");
        }
        return ans;
    }
}