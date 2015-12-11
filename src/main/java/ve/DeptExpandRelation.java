package ve;

import entity.BaseInfo;
import entity.VirtualEntity;

/**
 * Created by guofan on 2015/12/8.
 */
public class DeptExpandRelation {
    private String id;
    private BaseInfo dept;
    private String type;
    private Integer role;
    private VirtualEntity virtualEntity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BaseInfo getDept() {
        return dept;
    }

    public void setDept(BaseInfo dept) {
        this.dept = dept;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public VirtualEntity getVirtualEntity() {
        return virtualEntity;
    }

    public void setVirtualEntity(VirtualEntity virtualEntity) {
        this.virtualEntity = virtualEntity;
    }
}
