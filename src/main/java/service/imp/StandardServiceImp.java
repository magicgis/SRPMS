package service.imp;

import entity.Standard;
import org.springframework.stereotype.Service;
import service.StandardService;

import java.util.List;

@Service
public class StandardServiceImp extends BaseServiceImp<Standard> implements StandardService {
    @Override
    public List<Standard> search(String keyword, String sort, String order) {
        return null;
    }
}