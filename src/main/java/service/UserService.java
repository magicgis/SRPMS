package service;

import entity.User;

public interface UserService extends BaseService<User> {
    User getUser(String staId, String pwd);

    boolean editpasswd(User user, String pwd);
}