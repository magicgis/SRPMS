package service.imp;

import entity.Basebk;
import service.BasebkService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasebkServiceImp extends BaseServiceImp<Basebk> implements BasebkService {
    @Override
    public List<Basebk> search(String keyword, String sort, String order) {
        return null;
    }
}