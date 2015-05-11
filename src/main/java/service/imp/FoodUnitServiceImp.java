package service.imp;

import entity.FoodUnit;
import service.FoodUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodUnitServiceImp extends BaseServiceImp<FoodUnit> implements FoodUnitService {
    @Override
    public List<FoodUnit> search(String keyword, String sort, String order) {
        return null;
    }
}