package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "med_unit", schema = "", catalog = "srpms")
public class MedUnit {
    private String id;
    private Integer rank;
    private Med medByMedId;
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

        MedUnit medUnit = (MedUnit) o;

        if (id != null ? !id.equals(medUnit.id) : medUnit.id != null) return false;
        if (rank != null ? !rank.equals(medUnit.rank) : medUnit.rank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "med_id", referencedColumnName = "id")
    public Med getMedByMedId() {
        return medByMedId;
    }

    public void setMedByMedId(Med medByMedId) {
        this.medByMedId = medByMedId;
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
