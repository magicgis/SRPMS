package entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "med", schema = "", catalog = "srpms")
public class Med {
    private String id;
    private String name;
    private String type;
    private Integer scoreType;
    private String devpStage;
    private String clinCode;
    private String prodCode;
    private String medCertid;
    private String memo;
    private Collection<MedUnit> medUnitsById;
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
    @Column(name = "score_type")
    public Integer getScoreType() {
        return scoreType;
    }

    public void setScoreType(Integer scoreType) {
        this.scoreType = scoreType;
    }

    @Basic
    @Column(name = "devp_stage")
    public String getDevpStage() {
        return devpStage;
    }

    public void setDevpStage(String devpStage) {
        this.devpStage = devpStage;
    }

    @Basic
    @Column(name = "clin_code")
    public String getClinCode() {
        return clinCode;
    }

    public void setClinCode(String clinCode) {
        this.clinCode = clinCode;
    }

    @Basic
    @Column(name = "prod_code")
    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    @Basic
    @Column(name = "med_certid")
    public String getMedCertid() {
        return medCertid;
    }

    public void setMedCertid(String medCertid) {
        this.medCertid = medCertid;
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

        Med med = (Med) o;

        if (id != null ? !id.equals(med.id) : med.id != null) return false;
        if (name != null ? !name.equals(med.name) : med.name != null) return false;
        if (type != null ? !type.equals(med.type) : med.type != null) return false;
        if (scoreType != null ? !scoreType.equals(med.scoreType) : med.scoreType != null) return false;
        if (devpStage != null ? !devpStage.equals(med.devpStage) : med.devpStage != null) return false;
        if (clinCode != null ? !clinCode.equals(med.clinCode) : med.clinCode != null) return false;
        if (prodCode != null ? !prodCode.equals(med.prodCode) : med.prodCode != null) return false;
        if (medCertid != null ? !medCertid.equals(med.medCertid) : med.medCertid != null) return false;
        if (memo != null ? !memo.equals(med.memo) : med.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (scoreType != null ? scoreType.hashCode() : 0);
        result = 31 * result + (devpStage != null ? devpStage.hashCode() : 0);
        result = 31 * result + (clinCode != null ? clinCode.hashCode() : 0);
        result = 31 * result + (prodCode != null ? prodCode.hashCode() : 0);
        result = 31 * result + (medCertid != null ? medCertid.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "medByMedId")
    public Collection<MedUnit> getMedUnitsById() {
        return medUnitsById;
    }

    public void setMedUnitsById(Collection<MedUnit> medUnitsById) {
        this.medUnitsById = medUnitsById;
    }

    @OneToMany(mappedBy = "medByMedId")
    public Collection<StaRef> getStaRefsById() {
        return staRefsById;
    }

    public void setStaRefsById(Collection<StaRef> staRefsById) {
        this.staRefsById = staRefsById;
    }
}
