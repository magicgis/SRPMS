package service.imp;

import entity.AchTran;
import org.springframework.stereotype.Service;
import service.AchTranService;

import java.util.List;

/**
 * Created by guofan on 2015/10/22.
 */
@Service
public class AchTranServiceImp extends BaseServiceImp<AchTran> implements AchTranService {
    @Override
    public List<AchTran> search(String keyword, String sort, String order) {
        return null;
    }
}
