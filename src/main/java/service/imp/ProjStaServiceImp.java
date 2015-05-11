package service.imp;

import entity.ProjSta;
import service.ProjStaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjStaServiceImp extends BaseServiceImp<ProjSta> implements ProjStaService {
    @Override
    public List<ProjSta> search(String keyword, String sort, String order) {
        return null;
    }
}