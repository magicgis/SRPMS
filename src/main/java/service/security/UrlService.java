package service.security;

import entity.security.Url;
import service.BaseService;

/**
 * Created by guofan on 2015/5/8.
 */
public interface UrlService extends BaseService<Url> {
    boolean addUrl(String path, String type, String define);

    boolean deleteByPathAndType(String path, String type);

    boolean update(String path, String type, String define);
}
