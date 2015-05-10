package service.security.imp;

import dao.security.UrlDao;
import entity.security.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.imp.BaseServiceImp;
import service.security.UrlService;

import java.util.List;

/**
 * Created by guofan on 2015/5/9.
 */
@Service
public class UrlServiceImp extends BaseServiceImp<Url> implements UrlService {
    @Autowired
    UrlDao urlDao;

    public boolean addUrl(String path, String type, String define) {
        if (urlDao.getById(path + "-" + type) == null) {
            Url url = new Url();
            url.setId(path + "-" + type);
            url.setUrl(path);
            url.setType(type);
            url.setDefine(define);
            return urlDao.save(url);
        } else {
            return true;
        }
    }

    public boolean deleteByPathAndType(String path, String type) {
        Url url = urlDao.getById(path + "-" + type);
        if (url != null) {
            return urlDao.delete(url);
        } else {
            return false;
        }
    }

    public boolean update(String path, String type, String define) {
        Url url = urlDao.getById(path + "-" + type);
        if (url != null) {
            url.setDefine(define);
            return urlDao.update(url);
        } else {
            return false;
        }
    }

    @Override
    public List<Url> search(String keyword, String sort, String order) {
        return null;
    }
}
