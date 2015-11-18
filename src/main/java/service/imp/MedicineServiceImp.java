package service.imp;

import entity.Medicine;
import org.springframework.stereotype.Service;
import service.MedicineService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guofan on 2015/11/19.
 */
@Service
public class MedicineServiceImp extends BaseServiceImp<Medicine> implements MedicineService {
    @Override
    public List<Medicine> search(String keyword, String sort, String order) {
        return new ArrayList<>();
    }
}
