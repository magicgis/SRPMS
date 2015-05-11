package service.imp;

import entity.Basepat;
import service.BasepatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasepatServiceImp extends BaseServiceImp<Basepat> implements BasepatService {
    @Override
    public List<Basepat> search(String keyword, String sort, String order) {
        return null;
    }
}