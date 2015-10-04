package service;

import entity.Patent;

import java.util.List;

/**
 * Created by guofan on 2015/10/2.
 */
public interface PatentService extends BaseService<Patent> {
    public List<Patent> getAllUnStart();
}
