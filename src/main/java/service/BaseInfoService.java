package service;

import entity.BaseInfo;

import java.util.List;

public interface BaseInfoService extends BaseService<BaseInfo> {

    List getByTableName(String tableName);

    boolean addBaseInfo(BaseInfo baseInfo);

    String getNewID(String tableName);
}