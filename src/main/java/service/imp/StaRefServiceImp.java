package service.imp;

import entity.StaRef;
import service.StaRefService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaRefServiceImp extends BaseServiceImp<StaRef> implements StaRefService {
    @Override
    public List<StaRef> search(String keyword, String sort, String order) {
        return null;
    }
}