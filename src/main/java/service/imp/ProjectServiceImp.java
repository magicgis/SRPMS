package service.imp;

import entity.Project;
import service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp extends BaseServiceImp<Project> implements ProjectService {
    @Override
    public List<Project> search(String keyword, String sort, String order) {
        return null;
    }
}