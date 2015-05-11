package service.imp;

import entity.ApparUnit;
import service.ApparUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApparUnitServiceImp extends BaseServiceImp<ApparUnit> implements ApparUnitService {
    @Override
    public List<ApparUnit> search(String keyword, String sort, String order) {
        return null;
    }
}