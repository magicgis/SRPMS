package service.imp;

import entity.Standard;
import org.springframework.stereotype.Service;
import service.StandardInfoService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by guofan on 2015/9/24.
 */
@Service
public class StandardInfoServiceImp extends BaseServiceImp<Standard> implements StandardInfoService {
    @Override
    public List<Standard> search(String keyword, String sort, String order) {
        return new LinkedList<>();
    }
}
