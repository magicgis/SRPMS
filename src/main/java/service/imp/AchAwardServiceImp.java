package service.imp;

import dao.AchAwardDao;
import entity.AchAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AchAwardService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guofan on 2015/10/12.
 */
@Service
public class AchAwardServiceImp extends BaseServiceImp<AchAward> implements AchAwardService {
    @Autowired
    AchAwardDao achAwardDao;

    @Override
    public List<AchAward> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("name", "arg");
        return achAwardDao.findByArrayFuz(keys, keyword, sort, order);
    }
}
