package service.imp;

import entity.Paper;
import service.PaperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImp extends BaseServiceImp<Paper> implements PaperService {
    @Override
    public List<Paper> search(String keyword, String sort, String order) {
        return null;
    }
}