package service.security;

import java.io.Serializable;

/**
 * Created by guofan on 2015/6/29.
 */
public interface SecurityService {
    /**
     * 添加一条URL记录
     *
     * @param path   url路径
     * @param type   method
     * @param define 定义
     * @return 主键
     */
    Serializable addUrl(String path, String type, String define);

    /**
     * 删除URL
     *
     * @param path 路径
     * @param type method
     * @return T/F
     */
    boolean deleteByPathAndType(String path, String type);

    /**
     * 更新
     *
     * @param path   url路径
     * @param type   method
     * @param define 定义
     * @return T/F
     */
    boolean update(String path, String type, String define);

    /**
     * 添加角色组
     *
     * @param role   角色组
     * @param define 定义
     * @return T/F
     */
    boolean addRole(String role, String define);

    /**
     * 更新角色组
     *
     * @param role   角色
     * @param define 定义
     * @return T/F
     */
    boolean updateRole(String role, String define);

    /**
     * 删除角色组
     *
     * @param role 角色组
     * @return T/F
     */
    boolean removeRole(String role);

    /**
     * 将权限数据读入内存
     */
    void loadIntoCache();
}
