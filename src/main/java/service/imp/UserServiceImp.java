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
        keys.add("uStaff.name");
        keys.add("uStaff.id");
        keys.add("uStaff.sDept.value");
        keys.add("uStaff.degree");
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
}