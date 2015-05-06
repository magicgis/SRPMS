package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
public class Baseach {
    private String id;
    private String awdProp;
    private String awdType;
    private String awdRank;
    private Integer awdScore;
    private Integer awdMaxNum;
    private String noawdType;
    private Integer noawdScore;
    private Integer noawdMaxNum;
    private String apprType;
    private String apprRank;
    private Integer apprScore;
    private Integer apprMaxNum;
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
    @Column(name = "awd_prop")
    public String getAwdProp() {
        return awdProp;
    }

    public void setAwdProp(String awdProp) {
        this.awdProp = awdProp;
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
    @Column(name = "awd_score")
    public Integer getAwdScore() {
        return awdScore;
    }

    public void setAwdScore(Integer awdScore) {
        this.awdScore = awdScore;
    }

    @Basic
    @Column(name = "awd_max_num")
    public Integer getAwdMaxNum() {
        return awdMaxNum;
    }

    public void setAwdMaxNum(Integer awdMaxNum) {
        this.awdMaxNum = awdMaxNum;
    }

    @Basic
    @Column(name = "noawd_type")
    public String getNoawdType() {
        return noawdType;
    }

    public void setNoawdType(String noawdType) {
        this.noawdType = noawdType;
    }

    @Basic
    @Column(name = "noawd_score")
    public Integer getNoawdScore() {
        return noawdScore;
    }

    public void setNoawdScore(Integer noawdScore) {
        this.noawdScore = noawdScore;
    }

    @Basic
    @Column(name = "noawd_max_num")
    public Integer getNoawdMaxNum() {
        return noawdMaxNum;
    }

    public void setNoawdMaxNum(Integer noawdMaxNum) {
        this.noawdMaxNum = noawdMaxNum;
    }

    @Basic
    @Column(name = "appr_type")
    public String getApprType() {
        return apprType;
    }

    public void setApprType(String apprType) {
        this.apprType = apprType;
    }

    @Basic
    @Column(name = "appr_rank")
    public String getApprRank() {
        return apprRank;
    }

    public void setApprRank(String apprRank) {
        this.apprRank = apprRank;
    }

    @Basic
    @Column(name = "appr_score")
    public Integer getApprScore() {
        return apprScore;
    }

    public void setApprScore(Integer apprScore) {
        this.apprScore = apprScore;
    }

    @Basic
    @Column(name = "appr_max_num")
    public Integer getApprMaxNum() {
        return apprMaxNum;
    }

    public void setApprMaxNum(Integer apprMaxNum) {
        this.apprMaxNum = apprMaxNum;
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

        Baseach baseach = (Baseach) o;

        if (id != null ? !id.equals(baseach.id) : baseach.id != null) return false;
        if (awdProp != null ? !awdProp.equals(baseach.awdProp) : baseach.awdProp != null) return false;
        if (awdType != null ? !awdType.equals(baseach.awdType) : baseach.awdType != null) return false;
        if (awdRank != null ? !awdRank.equals(baseach.awdRank) : baseach.awdRank != null) return false;
        if (awdScore != null ? !awdScore.equals(baseach.awdScore) : baseach.awdScore != null) return false;
        if (awdMaxNum != null ? !awdMaxNum.equals(baseach.awdMaxNum) : baseach.awdMaxNum != null) return false;
        if (noawdType != null ? !noawdType.equals(baseach.noawdType) : baseach.noawdType != null) return false;
        if (noawdScore != null ? !noawdScore.equals(baseach.noawdScore) : baseach.noawdScore != null) return false;
        if (noawdMaxNum != null ? !noawdMaxNum.equals(baseach.noawdMaxNum) : baseach.noawdMaxNum != null) return false;
        if (apprType != null ? !apprType.equals(baseach.apprType) : baseach.apprType != null) return false;
        if (apprRank != null ? !apprRank.equals(baseach.apprRank) : baseach.apprRank != null) return false;
        if (apprScore != null ? !apprScore.equals(baseach.apprScore) : baseach.apprScore != null) return false;
        if (apprMaxNum != null ? !apprMaxNum.equals(baseach.apprMaxNum) : baseach.apprMaxNum != null) return false;
        if (memo != null ? !memo.equals(baseach.memo) : baseach.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (awdProp != null ? awdProp.hashCode() : 0);
        result = 31 * result + (awdType != null ? awdType.hashCode() : 0);
        result = 31 * result + (awdRank != null ? awdRank.hashCode() : 0);
        result = 31 * result + (awdScore != null ? awdScore.hashCode() : 0);
        result = 31 * result + (awdMaxNum != null ? awdMaxNum.hashCode() : 0);
        result = 31 * result + (noawdType != null ? noawdType.hashCode() : 0);
        result = 31 * result + (noawdScore != null ? noawdScore.hashCode() : 0);
        result = 31 * result + (noawdMaxNum != null ? noawdMaxNum.hashCode() : 0);
        result = 31 * result + (apprType != null ? apprType.hashCode() : 0);
        result = 31 * result + (apprRank != null ? apprRank.hashCode() : 0);
        result = 31 * result + (apprScore != null ? apprScore.hashCode() : 0);
        result = 31 * result + (apprMaxNum != null ? apprMaxNum.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
