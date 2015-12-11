package service.imp;

import dao.*;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.stereotype.Service;
import service.RelationService;
import util.Args;
import ve.DeptExpandRelation;
import ve.ExpandRelation;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Service
public class RelationServiceImp implements RelationService {

    @Autowired
    StaRefDao staRefDao;
    @Autowired
    DeptRefDao deptRefDao;
    @Autowired
    StaffDao staffDao;
    @Autowired
    BaseInfoDao baseInfoDao;

    @Autowired
    AbstractBeanFactory abstractBeanFactory;


    private static final Logger log = LogManager.getLogger(RelationServiceImp.class);

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
        /*清除库中已有的关系*/
        staRefDao.removeRelation(entity, type);
        deptRefDao.removeRelation(entity, type);
        HashMap<String, BigDecimal> userScore = new HashMap<>();
        HashMap<String, Integer> userRole = new HashMap<>();
        String mainActor = "";
        Integer testRole = 99;
        /*第一遍添加*/
        for (Map actor : actors) {
            String staffId = (String) actor.get("staff.id");
            Integer role = Integer.valueOf(actor.get("rank").toString());
            BigDecimal score = BigDecimal.valueOf(Double.valueOf(actor.get("score").toString()));
            if (Args.SPECIAL_STAFF_ID.contains(staffId)) {
                continue;
            }
            if (userScore.containsKey(staffId)) {
                /*当排位更小*/
                if (userRole.get(staffId) > role) {
                    userRole.put(staffId, role);
                    if (testRole > role) {
                        testRole = role;
                        mainActor = staffId;
                    }
                }
                /*当分数更高*/
                if (userScore.get(staffId).compareTo(score) < 0) {
                    userScore.put(staffId, score);
                }
            }
            else {
                userRole.put(staffId, role);
                userScore.put(staffId, score);
                if (testRole > role) {
                    testRole = role;
                    mainActor = staffId;
                }
            }
        }
        userRole.put(mainActor, 1);
        Set<BaseInfo> depts = new HashSet<>();
        BaseInfo mainDept = new BaseInfo();
        for (String staffId : userScore.keySet()) {
            Staff staff = staffDao.getById(staffId);
            StaRef staRef = new StaRef();
            staRef.setType(type);
            staRef.setEntityId(entity);
            staRef.setScore(userScore.get(staffId));
            if (userRole.get(staffId) == 1) {
                staRef.setRole(1);
                mainDept = staff.getCol();
            }
            else {
                staRef.setRole(0);
                depts.add(staff.getCol());
            }
            staRef.setStaff(staff);
            staRefDao.save(staRef);
        }
        if (depts.contains(mainDept)) {
            depts.remove(mainDept);
        }
        DeptRef main = new DeptRef();
        main.setType(type);
        main.setEntityId(entity);
        main.setRole(1);
        main.setDept(mainDept);
        deptRefDao.save(main);

        Iterator<BaseInfo> it = depts.iterator();
        while (it.hasNext()) {
            DeptRef deptRef = new DeptRef();
            deptRef.setType(type);
            deptRef.setEntityId(entity);
            deptRef.setRole(0);
            deptRef.setDept(it.next());
            deptRefDao.save(deptRef);
        }

    }

    @Override
    public List<DeptExpandRelation> getDeptRelation(String deptId, String type, Integer role) {
        List<DeptRef> res = deptRefDao.getByTypeAndRole(deptId, type, role);
        List<DeptExpandRelation> ans = new LinkedList<>();
        BaseDao dao = (BaseDao) abstractBeanFactory.getBean(Args.DAOS.get(type));
        for (DeptRef re : res) {
            DeptExpandRelation deptExpandRelation = new DeptExpandRelation();
            deptExpandRelation.setId(re.getId());
            deptExpandRelation.setDept(re.getDept());
            deptExpandRelation.setType(re.getType());
            deptExpandRelation.setVirtualEntity((VirtualEntity) dao.getById(re.getEntityId()));
            deptExpandRelation.setRole(re.getRole());
            ans.add(deptExpandRelation);
        }
        return ans;
    }

    @Override
    public List<ExpandRelation> getTeacherRelation(String staffId, String type, Integer role) {
        List<StaRef> res = staRefDao.getByTypeAndRole(staffId, type, role);
        List<ExpandRelation> ans = new LinkedList<>();
        BaseDao dao = (BaseDao) abstractBeanFactory.getBean(Args.DAOS.get(type));
        for (StaRef re : res) {
            ExpandRelation expandRelation = new ExpandRelation();
            expandRelation.setId(re.getId());
            expandRelation.setStaff(re.getStaff());
            expandRelation.setType(re.getType());
            expandRelation.setVirtualEntity((VirtualEntity) dao.getById(re.getEntityId()));
            expandRelation.setRole(re.getRole());
            expandRelation.setScore(re.getScore());
            ans.add(expandRelation);
        }
        return ans;
    }

    @Override
    public void removeRelation(String entityId, String type) {
        staRefDao.removeRelation(entityId, type);
        deptRefDao.removeRelation(entityId, type);
    }
}