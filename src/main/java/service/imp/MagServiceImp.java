package service.imp;

import dao.MagDao;
import entity.Mag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.MagService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MagServiceImp extends BaseServiceImp<Mag> implements MagService {
    @Autowired
    MagDao magDao;

    @Override
    public List<Mag> search(String keyword, String sort, String order) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("name");
        keys.add("standard.value");
        return magDao.findByArrayFuz(keys, keyword, sort, order);
    }
}