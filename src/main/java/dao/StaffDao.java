package dao;

import entity.Staff;

import java.util.List;

public interface StaffDao extends BaseDao<Staff> {
    public List<Staff> getStaffsByPriAndCol(Integer p, String colId);
}