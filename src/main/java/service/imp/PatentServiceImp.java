package service.imp;

import entity.Patent;
import service.PatentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatentServiceImp extends BaseServiceImp<Patent> implements PatentService {
    @Override
    public List<Patent> search(String keyword, String sort, String order) {
        return null;
    }
}