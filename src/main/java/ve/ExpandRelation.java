package ve;

import entity.Staff;
import entity.VirtualEntity;

import java.math.BigDecimal;

/**
 * Created by guofan on 2015/12/2.
 */
public class ExpandRelation {
    private String id;
    private String type;
    private Integer role;
    private BigDecimal score;
    private VirtualEntity virtualEntity;
    private Staff staff;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public VirtualEntity getVirtualEntity() {
        return virtualEntity;
    }

    public void setVirtualEntity(VirtualEntity virtualEntity) {
        this.virtualEntity = virtualEntity;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
