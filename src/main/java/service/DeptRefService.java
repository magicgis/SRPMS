package service;

import entity.BaseInfo;
import entity.DeptRef;

import java.util.List;

/**
 * Created by guofan on 2015/11/18.
 */
public interface DeptRefService extends BaseService<DeptRef> {

    /**
     * 获取关系中实体
     *
     * @param id staRef 主键
     * @return
     */
    Object getEntity(String id);

    Object getEntity(DeptRef deptRef);

    List<Object> getEntity(String id, String type);

    void insertRelation(String entityId, String type, BaseInfo dept);

}
