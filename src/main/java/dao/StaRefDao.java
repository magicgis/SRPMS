package dao;

import entity.StaRef;

import java.util.List;

public interface StaRefDao extends BaseDao<StaRef> {
    List<StaRef> getByTypeAndRole(String id, String type, Integer role);

    Boolean removeRelation(String entity, String type);
}