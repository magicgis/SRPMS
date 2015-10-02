package service.imp;

import dao.PatentDao;
import entity.Patent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PatentService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guofan on 2015/10/2.
 */
@Service
public class PatentServiceImp extends BaseServiceImp<Patent> implements PatentService {
    @Autowired
    PatentDao patentDao;

    @Override
    public List<Patent> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("name", "patentNo", "patentPubNo");
        return patentDao.findByArrayFuz(keys, keyword, sort, order);
    }
}
