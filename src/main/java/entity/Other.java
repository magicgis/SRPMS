package entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "other", schema = "", catalog = "srpms")
public class Other {
    private String id;
    private String name;
    private String prodNo;
    private String memo;
    private Collection<OtherUnit> otherUnitsById;
    private Collection<StaRef> staRefsById;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "prod_no")
    public String getProdNo() {
        return prodNo;
    }

    public void setProdNo(String prodNo) {
        this.prodNo = prodNo;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Other other = (Other) o;

        if (id != null ? !id.equals(other.id) : other.id != null) return false;
        if (name != null ? !name.equals(other.name) : other.name != null) return false;
        if (prodNo != null ? !prodNo.equals(other.prodNo) : other.prodNo != null) return false;
        if (memo != null ? !memo.equals(other.memo) : other.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (prodNo != null ? prodNo.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }


    @OneToMany(mappedBy = "otherByOtherId")
    public Collection<OtherUnit> getOtherUnitsById() {
        return otherUnitsById;
    }

    public void setOtherUnitsById(Collection<OtherUnit> otherUnitsById) {
        this.otherUnitsById = otherUnitsById;
    }

    @OneToMany(mappedBy = "otherByOtherId")
    public Collection<StaRef> getStaRefsById() {
        return staRefsById;
    }

    public void setStaRefsById(Collection<StaRef> staRefsById) {
        this.staRefsById = staRefsById;
    }
}
