package dao;

import entity.DeptRef;

import java.util.List;

/**
 * Created by guofan on 2015/11/18.
 */
public interface DeptRefDao extends BaseDao<DeptRef> {
    List<DeptRef> getByTypeAndRole(String id, String type, Integer role);

    List<DeptRef> getByType(String id, String type);

    List<DeptRef> get(String id);

    void removeRelation(String entity, String type);
}
