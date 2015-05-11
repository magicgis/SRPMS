package service.imp;

import entity.BaseInfo;
import service.BaseInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseInfoServiceImp extends BaseServiceImp<BaseInfo> implements BaseInfoService {
    @Override
    public List<BaseInfo> search(String keyword, String sort, String order) {
        return null;
    }
}