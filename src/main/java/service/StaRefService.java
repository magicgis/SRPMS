package service;

import entity.StaRef;

import java.util.List;
import java.util.Map;

public interface StaRefService extends BaseService<StaRef> {

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
     * todo
     * 这儿基本没有考虑特殊情况，以后需要添加
     * 比如学生，比如挂名多个单位
     *
     * @param entity
     * @param type
     * @param actors
     */
    void insertRelation(String entity, String type, List<Map> actors);

}