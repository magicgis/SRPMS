package service.imp;

import dao.PaperDao;
import entity.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import service.PaperService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaperServiceImp extends BaseServiceImp<Paper> implements PaperService {
    @Autowired
    PaperDao paperDao;

    @Override
    public List<Paper> search(String keyword, String sort, String order) {
        List<String> keys = Arrays.asList("name");
        return paperDao.findByArrayFuz(keys, keyword, sort, order);
    }
}