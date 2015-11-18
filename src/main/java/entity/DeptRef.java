package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by guofan on 2015/11/18.
 */
@Entity
@Table(name = "dept_ref")
public class DeptRef {
    String id;
    String entityId;
    String type;
    Integer role;
    String arg;

    BaseInfo dept;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "entity_id")
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @Basic
    @Column(name = "arg")
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeptRef deptRef = (DeptRef) o;

        if (id != null ? !id.equals(deptRef.id) : deptRef.id != null) return false;
        if (entityId != null ? !entityId.equals(deptRef.entityId) : deptRef.entityId != null) return false;
        if (type != null ? !type.equals(deptRef.type) : deptRef.type != null) return false;
        if (role != null ? !role.equals(deptRef.role) : deptRef.role != null) return false;
        if (arg != null ? !arg.equals(deptRef.arg) : deptRef.arg != null) return false;
        return !(dept != null ? !dept.equals(deptRef.dept) : deptRef.dept != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "dept", referencedColumnName = "id")
    public BaseInfo getDept() {
        return dept;
    }

    public void setDept(BaseInfo dept) {
        this.dept = dept;
    }
}
