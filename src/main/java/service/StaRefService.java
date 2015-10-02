package service;

import entity.StaRef;

import java.util.List;

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

}