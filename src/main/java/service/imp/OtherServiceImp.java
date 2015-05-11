package service.imp;

import entity.Other;
import service.OtherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OtherServiceImp extends BaseServiceImp<Other> implements OtherService {
    @Override
    public List<Other> search(String keyword, String sort, String order) {
        return null;
    }
}