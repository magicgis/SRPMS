package service;

import entity.Standard;

import java.util.List;

/**
 * Created by guofan on 2015/9/24.
 */
public interface StandardInfoService extends BaseService<Standard> {
    public List<Standard> getByType(String type);
}
