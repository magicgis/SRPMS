package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Basebk {
    private String id;
    private String type;
    private String editRole;
    private Integer selfScore;
    private BigDecimal otherScore;
    private String awdType;
    private String awdRank;
    private BigDecimal awdMult;
    private BigDecimal proMult;
    private BigDecimal xltMult;
    private String memo;

    @Id
    @Column(name = "id")
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
    @Column(name = "edit_role")
    public String getEditRole() {
        return editRole;
    }

    public void setEditRole(String editRole) {
        this.editRole = editRole;
    }

    @Basic
    @Column(name = "self_score")
    public Integer getSelfScore() {
        return selfScore;
    }

    public void setSelfScore(Integer selfScore) {
        this.selfScore = selfScore;
    }

    @Basic
    @Column(name = "other_score")
    public BigDecimal getOtherScore() {
        return otherScore;
    }

    public void setOtherScore(BigDecimal otherScore) {
        this.otherScore = otherScore;
    }

    @Basic
    @Column(name = "awd_type")
    public String getAwdType() {
        return awdType;
    }

    public void setAwdType(String awdType) {
        this.awdType = awdType;
    }

    @Basic
    @Column(name = "awd_rank")
    public String getAwdRank() {
        return awdRank;
    }

    public void setAwdRank(String awdRank) {
        this.awdRank = awdRank;
    }

    @Basic
    @Column(name = "awd_mult")
    public BigDecimal getAwdMult() {
        return awdMult;
    }

    public void setAwdMult(BigDecimal awdMult) {
        this.awdMult = awdMult;
    }

    @Basic
    @Column(name = "pro_mult")
    public BigDecimal getProMult() {
        return proMult;
    }

    public void setProMult(BigDecimal proMult) {
        this.proMult = proMult;
    }

    @Basic
    @Column(name = "xlt_mult")
    public BigDecimal getXltMult() {
        return xltMult;
    }

    public void setXltMult(BigDecimal xltMult) {
        this.xltMult = xltMult;
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

        Basebk basebk = (Basebk) o;

        if (id != null ? !id.equals(basebk.id) : basebk.id != null) return false;
        if (type != null ? !type.equals(basebk.type) : basebk.type != null) return false;
        if (editRole != null ? !editRole.equals(basebk.editRole) : basebk.editRole != null) return false;
        if (selfScore != null ? !selfScore.equals(basebk.selfScore) : basebk.selfScore != null) return false;
        if (otherScore != null ? !otherScore.equals(basebk.otherScore) : basebk.otherScore != null) return false;
        if (awdType != null ? !awdType.equals(basebk.awdType) : basebk.awdType != null) return false;
        if (awdRank != null ? !awdRank.equals(basebk.awdRank) : basebk.awdRank != null) return false;
        if (awdMult != null ? !awdMult.equals(basebk.awdMult) : basebk.awdMult != null) return false;
        if (proMult != null ? !proMult.equals(basebk.proMult) : basebk.proMult != null) return false;
        if (xltMult != null ? !xltMult.equals(basebk.xltMult) : basebk.xltMult != null) return false;
        if (memo != null ? !memo.equals(basebk.memo) : basebk.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (editRole != null ? editRole.hashCode() : 0);
        result = 31 * result + (selfScore != null ? selfScore.hashCode() : 0);
        result = 31 * result + (otherScore != null ? otherScore.hashCode() : 0);
        result = 31 * result + (awdType != null ? awdType.hashCode() : 0);
        result = 31 * result + (awdRank != null ? awdRank.hashCode() : 0);
        result = 31 * result + (awdMult != null ? awdMult.hashCode() : 0);
        result = 31 * result + (proMult != null ? proMult.hashCode() : 0);
        result = 31 * result + (xltMult != null ? xltMult.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
