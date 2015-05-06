package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Patent {
    private String id;
    private String name;
    private String scope;
    private String type;
    private String patGkNum;
    private String patNum;
    private Integer score;
    private Date patGgDate;
    private Date patHpDate;
    private String patStatus;
    private String endfill;
    private String memo;
    private Collection<Data> datasById;
    private Collection<PatUnit> patUnitsById;
    private BaseInfo baseInfoByDeptId;
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
    @Column(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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
    @Column(name = "pat_gk_num")
    public String getPatGkNum() {
        return patGkNum;
    }

    public void setPatGkNum(String patGkNum) {
        this.patGkNum = patGkNum;
    }

    @Basic
    @Column(name = "pat_num")
    public String getPatNum() {
        return patNum;
    }

    public void setPatNum(String patNum) {
        this.patNum = patNum;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "pat_gg_date")
    public Date getPatGgDate() {
        return patGgDate;
    }

    public void setPatGgDate(Date patGgDate) {
        this.patGgDate = patGgDate;
    }

    @Basic
    @Column(name = "pat_hp_date")
    public Date getPatHpDate() {
        return patHpDate;
    }

    public void setPatHpDate(Date patHpDate) {
        this.patHpDate = patHpDate;
    }

    @Basic
    @Column(name = "pat_status")
    public String getPatStatus() {
        return patStatus;
    }

    public void setPatStatus(String patStatus) {
        this.patStatus = patStatus;
    }

    @Basic
    @Column(name = "endfill")
    public String getEndfill() {
        return endfill;
    }

    public void setEndfill(String endfill) {
        this.endfill = endfill;
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

        Patent patent = (Patent) o;

        if (id != null ? !id.equals(patent.id) : patent.id != null) return false;
        if (name != null ? !name.equals(patent.name) : patent.name != null) return false;
        if (scope != null ? !scope.equals(patent.scope) : patent.scope != null) return false;
        if (type != null ? !type.equals(patent.type) : patent.type != null) return false;
        if (patGkNum != null ? !patGkNum.equals(patent.patGkNum) : patent.patGkNum != null) return false;
        if (patNum != null ? !patNum.equals(patent.patNum) : patent.patNum != null) return false;
        if (score != null ? !score.equals(patent.score) : patent.score != null) return false;
        if (patGgDate != null ? !patGgDate.equals(patent.patGgDate) : patent.patGgDate != null) return false;
        if (patHpDate != null ? !patHpDate.equals(patent.patHpDate) : patent.patHpDate != null) return false;
        if (patStatus != null ? !patStatus.equals(patent.patStatus) : patent.patStatus != null) return false;
        if (endfill != null ? !endfill.equals(patent.endfill) : patent.endfill != null) return false;
        if (memo != null ? !memo.equals(patent.memo) : patent.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (patGkNum != null ? patGkNum.hashCode() : 0);
        result = 31 * result + (patNum != null ? patNum.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (patGgDate != null ? patGgDate.hashCode() : 0);
        result = 31 * result + (patHpDate != null ? patHpDate.hashCode() : 0);
        result = 31 * result + (patStatus != null ? patStatus.hashCode() : 0);
        result = 31 * result + (endfill != null ? endfill.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "patentByPatentId")
    public Collection<Data> getDatasById() {
        return datasById;
    }

    public void setDatasById(Collection<Data> datasById) {
        this.datasById = datasById;
    }

    @OneToMany(mappedBy = "patentByPatId")
    public Collection<PatUnit> getPatUnitsById() {
        return patUnitsById;
    }

    public void setPatUnitsById(Collection<PatUnit> patUnitsById) {
        this.patUnitsById = patUnitsById;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    public BaseInfo getBaseInfoByDeptId() {
        return baseInfoByDeptId;
    }

    public void setBaseInfoByDeptId(BaseInfo baseInfoByDeptId) {
        this.baseInfoByDeptId = baseInfoByDeptId;
    }

    @OneToMany(mappedBy = "patentByPatentId")
    public Collection<StaRef> getStaRefsById() {
        return staRefsById;
    }

    public void setStaRefsById(Collection<StaRef> staRefsById) {
        this.staRefsById = staRefsById;
    }
}
