package service.imp;

import entity.ProjUnit;
import service.ProjUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjUnitServiceImp extends BaseServiceImp<ProjUnit> implements ProjUnitService {
    @Override
    public List<ProjUnit> search(String keyword, String sort, String order) {
        return null;
    }
}