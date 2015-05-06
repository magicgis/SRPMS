package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "other_unit", schema = "", catalog = "srpms")
public class OtherUnit {
    private String id;
    private Integer rank;
    private Other otherByOtherId;
    private BaseInfo baseInfoByUnitId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OtherUnit otherUnit = (OtherUnit) o;

        if (id != null ? !id.equals(otherUnit.id) : otherUnit.id != null) return false;
        if (rank != null ? !rank.equals(otherUnit.rank) : otherUnit.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "other_id", referencedColumnName = "id")
    public Other getOtherByOtherId() {
        return otherByOtherId;
    }

    public void setOtherByOtherId(Other otherByOtherId) {
        this.otherByOtherId = otherByOtherId;
    }

    @ManyToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    public BaseInfo getBaseInfoByUnitId() {
        return baseInfoByUnitId;
    }

    public void setBaseInfoByUnitId(BaseInfo baseInfoByUnitId) {
        this.baseInfoByUnitId = baseInfoByUnitId;
    }
}
