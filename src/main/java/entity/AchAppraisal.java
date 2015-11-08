package entity;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

import static util.Trans.argMap;

/**
 * Created by guofan on 2015/10/12.
 */
@Entity
@Table(name = "ach_appraisal", schema = "", catalog = "srpms")
public class AchAppraisal {
    private String id;
    private String name;
    private String regNo;
    private String regDate;
    private String certificateNo;
    private String certifyUnit;
    private String date;
    private String way;
    private Integer score;
    private String arg;
    private String achType;
    private String process;
    private BaseInfo dept;
    private Standard standard;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "reg_no", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    @Basic
    @Column(name = "reg_date", nullable = true, insertable = true, updatable = true, length = 255)
    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    @Basic
    @Column(name = "certificate_no", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    @Basic
    @Column(name = "certify_unit", nullable = true, insertable = true, updatable = true, length = 255)
    public String getCertifyUnit() {
        return certifyUnit;
    }

    public void setCertifyUnit(String certifyUnit) {
        this.certifyUnit = certifyUnit;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "way", nullable = true, insertable = true, updatable = true, length = 255)
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "arg", nullable = true, insertable = true, updatable = true, length = 255)
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
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

    @Basic
    @Column(name = "ach_type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAchType() {
        return achType;
    }

    public void setAchType(String achType) {
        this.achType = achType;
    }

    @Basic
    @Column(name = "process", nullable = true, insertable = true, updatable = true, length = 255)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AchAppraisal that = (AchAppraisal) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (regNo != null ? !regNo.equals(that.regNo) : that.regNo != null) return false;
        if (regDate != null ? !regDate.equals(that.regDate) : that.regDate != null) return false;
        if (certificateNo != null ? !certificateNo.equals(that.certificateNo) : that.certificateNo != null)
            return false;
        if (certifyUnit != null ? !certifyUnit.equals(that.certifyUnit) : that.certifyUnit != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (way != null ? !way.equals(that.way) : that.way != null) return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (arg != null ? !arg.equals(that.arg) : that.arg != null) return false;
        if (achType != null ? !achType.equals(that.achType) : that.achType != null) return false;
        if (process != null ? !process.equals(that.process) : that.process != null) return false;
        if (dept != null ? !dept.equals(that.dept) : that.dept != null) return false;
        return !(standard != null ? !standard.equals(that.standard) : that.standard != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (regNo != null ? regNo.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        result = 31 * result + (certificateNo != null ? certificateNo.hashCode() : 0);
        result = 31 * result + (certifyUnit != null ? certifyUnit.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (way != null ? way.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (achType != null ? achType.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "standard", referencedColumnName = "id")
    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

}
