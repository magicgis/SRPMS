package service.imp;

import dao.AchTranDao;
import entity.AchTran;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AchTranService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guofan on 2015/10/22.
 */
@Service
public class AchTranServiceImp extends BaseServiceImp<AchTran> implements AchTranService {
    @Autowired
    AchTranDao achTranDao;

    @Override
    public List<AchTran> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("name", "arg");
        return achTranDao.findByArrayFuz(keys, keyword, sort, order);
    }
}
