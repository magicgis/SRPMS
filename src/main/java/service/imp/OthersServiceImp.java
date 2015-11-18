package service.imp;

import entity.Others;
import org.springframework.stereotype.Service;
import service.OthersService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guofan on 2015/11/19.
 */
@Service
public class OthersServiceImp extends BaseServiceImp<Others> implements OthersService {
    @Override
    public List<Others> search(String keyword, String sort, String order) {
        return new ArrayList<>();
    }
}
