package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "pat_unit", schema = "", catalog = "srpms")
public class PatUnit {
    private String id;
    private Integer rank;
    private Patent patentByPatId;
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

        PatUnit patUnit = (PatUnit) o;

        if (id != null ? !id.equals(patUnit.id) : patUnit.id != null) return false;
        if (rank != null ? !rank.equals(patUnit.rank) : patUnit.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "pat_id", referencedColumnName = "id")
    public Patent getPatentByPatId() {
        return patentByPatId;
    }

    public void setPatentByPatId(Patent patentByPatId) {
        this.patentByPatId = patentByPatId;
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
