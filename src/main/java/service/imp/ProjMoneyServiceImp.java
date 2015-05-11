package service.imp;

import entity.ProjMoney;
import service.ProjMoneyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjMoneyServiceImp extends BaseServiceImp<ProjMoney> implements ProjMoneyService {
    @Override
    public List<ProjMoney> search(String keyword, String sort, String order) {
        return null;
    }
}