package service.imp;

import entity.Data;
import service.DataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServiceImp extends BaseServiceImp<Data> implements DataService {
    @Override
    public List<Data> search(String keyword, String sort, String order) {
        return null;
    }
}