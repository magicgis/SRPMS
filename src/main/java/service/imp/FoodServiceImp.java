package service.imp;

import entity.Food;
import org.springframework.stereotype.Service;
import service.FoodService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guofan on 2015/11/19.
 */
@Service
public class FoodServiceImp extends BaseServiceImp<Food> implements FoodService {
    @Override
    public List<Food> search(String keyword, String sort, String order) {
        return new ArrayList<>();
    }
}
