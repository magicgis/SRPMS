package service.imp;

import dao.StaffDao;
import entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StaffService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffServiceImp extends BaseServiceImp<Staff> implements StaffService {
    @Autowired
    StaffDao staffDao;

    @Override
    public List<Staff> search(String keyword, String sort, String order) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("id");
        keys.add("name");
        keys.add("position");
        keys.add("col.value");
        keys.add("dept.value");
        return staffDao.findByArrayFuz(keys, keyword, sort, order);
    }
}