package dao.imp;

import entity.User;
import dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp extends BaseDaoImp<User> implements UserDao {
}