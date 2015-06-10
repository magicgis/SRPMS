package service.imp;

import entity.Staff;
import org.springframework.stereotype.Service;
import service.StaffService;

import java.util.List;

@Service
public class StaffServiceImp extends BaseServiceImp<Staff> implements StaffService {
    @Override
    public List<Staff> search(String keyword, String sort, String order) {
        return null;
    }
}