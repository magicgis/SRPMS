package service.imp;

import entity.AchUnit;
import service.AchUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchUnitServiceImp extends BaseServiceImp<AchUnit> implements AchUnitService {
    @Override
    public List<AchUnit> search(String keyword, String sort, String order) {
        return null;
    }
}