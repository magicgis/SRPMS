package service.imp;

import dao.AchAppraisalDao;
import entity.AchAppraisal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AchAppraisalService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guofan on 2015/10/12.
 */
@Service
public class AchAppraisalServiceImp extends BaseServiceImp<AchAppraisal> implements AchAppraisalService {
    @Autowired
    AchAppraisalDao achAppraisalDao;

    @Override
    public List<AchAppraisal> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("name", "arg");
        return achAppraisalDao.findByArrayFuz(keys, keyword, sort, order);
    }
}
