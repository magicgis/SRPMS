package service.imp;

import dao.ProjectDao;
import entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ProjectService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guofan on 2015/8/12.
 */
@Service
public class ProjectServiceImp extends BaseServiceImp<Project> implements ProjectService {
    @Autowired
    ProjectDao projectDao;

    @Override
    public List<Project> search(String keyword, String sort, String order) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("code");
        keys.add("name");
        keys.add("dept.value");
        return projectDao.findByArrayFuz(keys, keyword, sort, order);
    }
}
