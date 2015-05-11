package service.imp;

import entity.Appar;
import service.ApparService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApparServiceImp extends BaseServiceImp<Appar> implements ApparService {
    @Override
    public List<Appar> search(String keyword, String sort, String order) {
        return null;
    }
}