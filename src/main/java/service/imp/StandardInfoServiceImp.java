package service.imp;

import dao.StandardDao;
import entity.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StandardInfoService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guofan on 2015/9/24.
 */
@Service
public class StandardInfoServiceImp extends BaseServiceImp<Standard> implements StandardInfoService {
    @Autowired
    StandardDao standardDao;

    @Override
    public List<Standard> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("type", "id", "info");
        return standardDao.findByArrayFuz(keys, keyword, sort, order);
    }

    @Override
    public List<Standard> getByType(String type) {
        return standardDao.findByPropertyA("type", type);
    }
}
