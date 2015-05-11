package service.imp;

import entity.PatUnit;
import service.PatUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatUnitServiceImp extends BaseServiceImp<PatUnit> implements PatUnitService {
    @Override
    public List<PatUnit> search(String keyword, String sort, String order) {
        return null;
    }
}