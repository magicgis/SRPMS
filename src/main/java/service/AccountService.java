package service;

import entity.Account;
import entity.Staff;

/**
 * DATE:2015/2/2
 * TIME:21:45
 * Created by guofan on 2015/2/2
 * 账户相关接口
 */
public interface AccountService {
    /**
     * 判断是否允许登陆
     * @param staId 工号
     * @param password 密码
     * @return 字符串 用户类型
     * "admin":管理员
     * "school":学校
     * "teacher":老师
     *  null:帐号或者密码错误
     */
    String getAccountType(String staId, String password);

    /**
     * 修改密码
     * @param account 帐号
     * @param password 新密码
     * @return
     */
    boolean setPassword(Account account, String password);

    /**
     * 修改邮箱
     * @param account 帐号
     * @param email 新邮箱
     * @return
     */
    boolean setEmail(Account account, String email);

    /**
     * 根据工号获取用户
     * @param staId
     * @return
     */
    Account getAccountById(String staId);

    /**
     * 重置随机密码
     * @param account 用户
     * @return 重置之后的随机密码
     */
    String setRandomPassword(Account account);

    /**
     * 新增登陆帐号
     * 应该只发挥“启用帐号”的作用
     * @param staff 员工实体
     * @param email 邮箱
     * @param pwd 密码
     * @param privilege 权限
     * @return boolean
     */
    boolean addAccount(Staff staff, String email, String pwd, String privilege);

    /**
     * 删除登陆帐号
     * 应该只发挥“停用帐号”的作用，而不影响员工实体
     * @param account 帐号实体
     * @return boolean
     */
    boolean delAccount(Account account);
    
}
