package service.imp;

import entity.OtherUnit;
import service.OtherUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherUnitServiceImp extends BaseServiceImp<OtherUnit> implements OtherUnitService {
    @Override
    public List<OtherUnit> search(String keyword, String sort, String order) {
        return null;
    }
}