package service;

import entity.Staff;

import java.util.HashMap;
import java.util.List;

/**
 * DATE:2015/2/12
 * TIME:0:29
 * Created by guofan on 2015/2/12
 * 员工信息相关接口
 */
public interface StaffService {
    /**
     * 获取员工信息
     * @param staId 工号
     * @return 实体 obj
     */
    Staff getStaff(String staId);

    /**
     * 获取所有员工信息
     * @return 所有员工信息 obj list
     */
    List<Staff> getStaff();

    /**
     * 根据部门名查找
     * @param deptName 部门名
     * @return obj list
     */
    List<Staff> getStaffByDept(String deptName);

    /**
     * 根据姓名查找查找
     * @param name 员工姓名
     * @return obj list
     */
    List<Staff> getStaffByName(String name);

    /**
     * 根据传入的key-value来进行组合查找,自由组合
     * 支持staName,deptId,IDCard,rank,edu,position，degree
     * @param keyword <PropertyName,PropertyValue>
     * @return obj list
     */
    List<Staff> getStaff(HashMap<String, String> keyword);

    /**
     * 添加员工信息
     * 需额外添加登陆信息才能登陆
     * @param staff 新员工
     * @return boolean
     */
    Boolean AddStaff(Staff staff);

    /**
     * 修改员工信息
     * @param staff 修改后的员工信息
     * @return boolean
     */
    Boolean EditStaff(Staff staff);

}
