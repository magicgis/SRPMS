package service.imp;

import entity.Project;
import service.ProjectService;

import java.util.List;

/**
 * Created by guofan on 2015/8/12.
 */
public class ProjectServiceImp extends BaseServiceImp<Project> implements ProjectService {
    @Override
    public List<Project> search(String keyword, String sort, String order) {
        return null;
    }
}
