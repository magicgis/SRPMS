package entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Appar {
    private String id;
    private String name;
    private String type;
    private String prodNo;
    private String memo;
    private Collection<ApparUnit> apparUnitsById;
    private Collection<Data> datasById;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

        Appar appar = (Appar) o;

        if (id != null ? !id.equals(appar.id) : appar.id != null) return false;
        if (name != null ? !name.equals(appar.name) : appar.name != null) return false;
        if (type != null ? !type.equals(appar.type) : appar.type != null) return false;
        if (prodNo != null ? !prodNo.equals(appar.prodNo) : appar.prodNo != null) return false;
        if (memo != null ? !memo.equals(appar.memo) : appar.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (prodNo != null ? prodNo.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "apparByApparId")
    public Collection<ApparUnit> getApparUnitsById() {
        return apparUnitsById;
    }

    public void setApparUnitsById(Collection<ApparUnit> apparUnitsById) {
        this.apparUnitsById = apparUnitsById;
    }

    @OneToMany(mappedBy = "apparByApparId")
    public Collection<Data> getDatasById() {
        return datasById;
    }

    public void setDatasById(Collection<Data> datasById) {
        this.datasById = datasById;
    }

    @OneToMany(mappedBy = "apparByApparId")
    public Collection<StaRef> getStaRefsById() {
        return staRefsById;
    }

    public void setStaRefsById(Collection<StaRef> staRefsById) {
        this.staRefsById = staRefsById;
    }
}
