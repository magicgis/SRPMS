package service.imp;

import entity.Patent;
import org.springframework.stereotype.Service;
import service.PatentService;

import java.util.List;

/**
 * Created by guofan on 2015/10/2.
 */
@Service
public class PatentServiceImp extends BaseServiceImp<Patent> implements PatentService {
    @Override
    public List<Patent> search(String keyword, String sort, String order) {
        return null;
    }
}
