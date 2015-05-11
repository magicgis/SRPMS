package service.imp;

import entity.Achievement;
import service.AchievementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImp extends BaseServiceImp<Achievement> implements AchievementService {
    @Override
    public List<Achievement> search(String keyword, String sort, String order) {
        return null;
    }
}