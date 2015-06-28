package service.imp;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.Trans.MD5;

@Service
public class UserServiceImp extends BaseServiceImp<User> implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public List<User> search(String keyword, String sort, String order) {
        ArrayList<String> keys = new ArrayList<>();
        keys.add("staff.name");
        keys.add("staff.id");
        keys.add("staff.dept.value");
        keys.add("staff.degree");
        return userDao.findByArrayFuz(keys, keyword, sort, order);
    }

    @Override
    public User getUser(String staId, String pwd) {
        HashMap<String, Object> args = new HashMap<>();
        args.put("id", staId);
        try {
            args.put("pwd", MD5(staId + pwd));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<User> temp = userDao.findByMapAcc(args);
        if (temp == null || temp.size() == 0) {
            return null;
        } else {
            return temp.get(0);
        }
    }

    @Override
    public boolean editpasswd(User user, String pwd) {
        if (user == null) {
            return false;
        }
        user.setPwd(MD5(user.getId() + pwd));
        return userDao.update(user);
    }
}