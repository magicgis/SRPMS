package service.imp;

import dao.FoodDao;
import entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FoodService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by guofan on 2015/11/19.
 */
@Service
public class FoodServiceImp extends BaseServiceImp<Food> implements FoodService {
    @Autowired
    FoodDao foodDao;

    @Override
    public List<Food> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("name", "date");
        return foodDao.findByArrayFuz(keys, keyword, sort, order);
    }
}
