package service.imp;

import entity.MedUnit;
import service.MedUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedUnitServiceImp extends BaseServiceImp<MedUnit> implements MedUnitService {
    @Override
    public List<MedUnit> search(String keyword, String sort, String order) {
        return null;
    }
}