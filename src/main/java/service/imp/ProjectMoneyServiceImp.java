package service.imp;

import entity.ProjectMoney;
import org.springframework.stereotype.Service;
import service.ProjectMoneyService;

import java.util.List;

/**
 * Created by guofan on 2015/8/12.
 */
@Service
public class ProjectMoneyServiceImp extends BaseServiceImp<ProjectMoney> implements ProjectMoneyService {
    @Override
    public List<ProjectMoney> search(String keyword, String sort, String order) {
        return null;
    }
}
