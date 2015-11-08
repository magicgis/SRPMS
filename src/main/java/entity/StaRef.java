package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "sta_ref")
public class StaRef {
    private String id;
    private String type;
    private Integer role;
    private BigDecimal score;
    private String unit;
    private String entityId;
    private String arg;
    private Staff staff;

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
    @Column(name = "score")
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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

        StaRef staRef = (StaRef) o;

        if (id != null ? !id.equals(staRef.id) : staRef.id != null) return false;
        if (type != null ? !type.equals(staRef.type) : staRef.type != null) return false;
        if (role != null ? !role.equals(staRef.role) : staRef.role != null) return false;
        if (score != null ? !score.equals(staRef.score) : staRef.score != null) return false;
        if (unit != null ? !unit.equals(staRef.unit) : staRef.unit != null) return false;
        if (entityId != null ? !entityId.equals(staRef.entityId) : staRef.entityId != null) return false;
        if (arg != null ? !arg.equals(staRef.arg) : staRef.arg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "staff", referencedColumnName = "id")
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
