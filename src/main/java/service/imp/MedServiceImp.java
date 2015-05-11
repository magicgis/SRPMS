package service.imp;

import entity.Med;
import service.MedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedServiceImp extends BaseServiceImp<Med> implements MedService {
    @Override
    public List<Med> search(String keyword, String sort, String order) {
        return null;
    }
}