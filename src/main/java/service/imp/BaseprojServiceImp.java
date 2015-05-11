package service.imp;

import entity.Baseproj;
import service.BaseprojService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseprojServiceImp extends BaseServiceImp<Baseproj> implements BaseprojService {
    @Override
    public List<Baseproj> search(String keyword, String sort, String order) {
        return null;
    }
}