package service.imp;

import dao.BasepaperDao;
import entity.Basepaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BasepaperService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasepaperServiceImp extends BaseServiceImp<Basepaper> implements BasepaperService {
    @Autowired
    BasepaperDao basepaperDao;

    @Override
    public List<Basepaper> search(String keyword, String sort, String order) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("colType");
        keys.add("type");
        return basepaperDao.findByArrayFuz(keys, keyword, sort, order);
    }

}