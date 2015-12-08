package service;

import ve.DeptExpandRelation;
import ve.ExpandRelation;
import entity.StaRef;

import java.util.List;
import java.util.Map;

public interface RelationService {

    /**
     * 获取某实体所对应的所有相关人
     *
     * @param entity 实体对象
     * @return 列表
     */
    List<StaRef> getUserList(Object entity);

    /**
     * 获取关系中实体
     *
     * @param id staRef 主键
     * @return
     */
    Object getEntity(String id);

    Object getEntity(StaRef staRef);

    List<Object> getEntity(String id, String type, Integer role);

    List<Object> getEntity(String id, Integer role);

    /**
     * 获取用户所关联的所有信息
     *
     * @param staffId 员工id 必选
     * @param type    类型 可选
     * @param role    角色 可选
     * @return
     */
    List<ExpandRelation> getTeacherRelation(String staffId, String type, Integer role);

    List<DeptExpandRelation> getDeptRelation(String deptId, String type, Integer role);

    /**
     * todo
     * 维护table关系
     *
     * @param entity 实体信息
     * @param type   类型
     * @param actors
     */
    void insertRelation(String entity, String type, List<Map> actors);


}