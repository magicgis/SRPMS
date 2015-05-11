package service.imp;

import entity.Newspaper;
import service.NewspaperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewspaperServiceImp extends BaseServiceImp<Newspaper> implements NewspaperService {
    @Override
    public List<Newspaper> search(String keyword, String sort, String order) {
        return null;
    }
}