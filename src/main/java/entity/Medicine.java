package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Map;

import static util.Trans.argMap;

/**
 * Created by guofan on 2015/11/19.
 */
@Entity
@Table(name = "medicine")
public class Medicine {
    private String id;
    private String name;
    private String date;
    private String type;
    private String childType;
    private String stage;
    private String clinCode;
    private String prodCode;
    private String medCertid;
    private Integer score;
    private String process;
    private String arg;

    private BaseInfo dept;
    private Standard standard;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date", nullable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "child_type", nullable = true, length = 255)
    public String getChildType() {
        return childType;
    }

    public void setChildType(String childType) {
        this.childType = childType;
    }

    @Basic
    @Column(name = "stage", nullable = true, length = 255)
    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Basic
    @Column(name = "clin_code", nullable = true, length = 255)
    public String getClinCode() {
        return clinCode;
    }

    public void setClinCode(String clinCode) {
        this.clinCode = clinCode;
    }

    @Basic
    @Column(name = "prod_code", nullable = true, length = 255)
    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    @Basic
    @Column(name = "med_certid", nullable = true, length = 255)
    public String getMedCertid() {
        return medCertid;
    }

    public void setMedCertid(String medCertid) {
        this.medCertid = medCertid;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "process", nullable = true, length = 255)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "arg", nullable = true, length = 2000)
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

        Medicine medicine = (Medicine) o;

        if (id != null ? !id.equals(medicine.id) : medicine.id != null) return false;
        if (name != null ? !name.equals(medicine.name) : medicine.name != null) return false;
        if (date != null ? !date.equals(medicine.date) : medicine.date != null) return false;
        if (dept != null ? !dept.equals(medicine.dept) : medicine.dept != null) return false;
        if (standard != null ? !standard.equals(medicine.standard) : medicine.standard != null) return false;
        if (type != null ? !type.equals(medicine.type) : medicine.type != null) return false;
        if (childType != null ? !childType.equals(medicine.childType) : medicine.childType != null) return false;
        if (stage != null ? !stage.equals(medicine.stage) : medicine.stage != null) return false;
        if (clinCode != null ? !clinCode.equals(medicine.clinCode) : medicine.clinCode != null) return false;
        if (prodCode != null ? !prodCode.equals(medicine.prodCode) : medicine.prodCode != null) return false;
        if (medCertid != null ? !medCertid.equals(medicine.medCertid) : medicine.medCertid != null) return false;
        if (score != null ? !score.equals(medicine.score) : medicine.score != null) return false;
        if (process != null ? !process.equals(medicine.process) : medicine.process != null) return false;
        if (arg != null ? !arg.equals(medicine.arg) : medicine.arg != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (childType != null ? childType.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (clinCode != null ? clinCode.hashCode() : 0);
        result = 31 * result + (prodCode != null ? prodCode.hashCode() : 0);
        result = 31 * result + (medCertid != null ? medCertid.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        return result;
    }


    @Transient
    public Map getArgMap() {
        return argMap(this.arg);
    }

    public void setArgMap(Map argMap) {
        try {
            this.arg = new ObjectMapper().writeValueAsString(argMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @ManyToOne
    @JoinColumn(name = "dept", referencedColumnName = "id")
    public BaseInfo getDept() {
        return dept;
    }

    public void setDept(BaseInfo dept) {
        this.dept = dept;
    }

    @ManyToOne
    @JoinColumn(name = "standard", referencedColumnName = "id")
    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

}
