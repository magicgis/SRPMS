package service.imp;

import entity.Basepaper;
import service.BasepaperService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasepaperServiceImp extends BaseServiceImp<Basepaper> implements BasepaperService {
    @Override
    public List<Basepaper> search(String keyword, String sort, String order) {
        return null;
    }
}