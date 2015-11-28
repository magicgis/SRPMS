package service.imp;

import dao.BaseDao;
import dao.PaperDao;
import dao.StaRefDao;
import dao.StaffDao;
import entity.StaRef;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.stereotype.Service;
import service.StaRefService;
import util.Args;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class StaRefServiceImp extends BaseServiceImp<StaRef> implements StaRefService {

    @Autowired
    StaRefDao staRefDao;
    @Autowired
    StaffDao staffDao;
    @Autowired
    PaperDao paperDao;
    @Autowired
    AbstractBeanFactory abstractBeanFactory;


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
            query.put("type", type.getSimpleName().toLowerCase());
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
            BaseDao dao = (BaseDao) abstractBeanFactory.getBean(Args.DAOS.get(type));
            ans = dao.getById(staRef.getEntityId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("哪儿好像出错了");
        }
        return ans;
    }

    @Override
    public List<Object> getEntity(String id, String type, Integer role) {
        List<StaRef> res = staRefDao.getByTypeAndRole(id, type, role);
        List<Object> ans = new LinkedList<>();
        BaseDao dao = (BaseDao) abstractBeanFactory.getBean(Args.DAOS.get(type));
        for (StaRef re : res) {
            ans.add(dao.getById(re.getEntityId()));
        }
        return ans;
    }

    /**
     * todo 估计性能差到爆表，当然，性能不重要
     *
     * @param id
     * @param role
     * @return
     */
    @Override
    public List<Object> getEntity(String id, Integer role) {
        List<StaRef> res = staRefDao.getByTypeAndRole(id, null, role);
        List<Object> ans = new LinkedList<>();
        for (StaRef re : res) {
            String meType = re.getType();
            BaseDao dao = (BaseDao) abstractBeanFactory.getBean(Args.DAOS.get(meType));
            ans.add(dao.getById(re.getEntityId()));
        }
        return ans;
    }

    @Override
    public void insertRelation(String entity, String type, List<Map> actors) {
        staRefDao.removeRelation(entity, type);
        for (Map actor : actors) {
            StaRef staRef = new StaRef();
            staRef.setEntityId(entity);
            staRef.setType(type);
            BigDecimal score = BigDecimal.valueOf(Double.valueOf((String) actor.get("score")));
            if (score.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            staRef.setScore(score);
            staRef.setRole("1".equals(actor.get("rank")) ? 1 : 0);
            staRef.setStaff(staffDao.getById((String) actor.get("staff.id")));
            staRef.setUnit((String) actor.get("unit"));
            staRefDao.save(staRef);
        }
    }

}