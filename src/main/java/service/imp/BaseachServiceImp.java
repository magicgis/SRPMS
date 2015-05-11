package service.imp;

import entity.Baseach;
import service.BaseachService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseachServiceImp extends BaseServiceImp<Baseach> implements BaseachService {
    @Override
    public List<Baseach> search(String keyword, String sort, String order) {
        return null;
    }
}