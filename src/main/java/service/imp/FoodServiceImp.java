package service.imp;

import entity.Food;
import service.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImp extends BaseServiceImp<Food> implements FoodService {
    @Override
    public List<Food> search(String keyword, String sort, String order) {
        return null;
    }
}