package service;

import entity.BaseInfo;

import java.util.List;

public interface BaseInfoService extends BaseService<BaseInfo> {

    List<BaseInfo> search(String keyword, String sort, String order);

    List getByTableName(String tableName);

    boolean addBaseInfo(BaseInfo baseInfo);

    String getNewID(String tableName);
}