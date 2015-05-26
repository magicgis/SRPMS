package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "baseproj", schema = "", catalog = "srpms")
public class Baseproj {
    private String id;
    private String projType;
    private String projRank;
    private String rateUnit;
    private String rateType;
    private Integer maxNum;
    private Integer apprScore;
    private Integer solScore;
    private Integer minMny;
    private Integer maxMny;
    private Integer mnyBaseScore;
    private Integer unapprScore;
    private Integer unapprNum;
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
    @Column(name = "proj_type")
    public String getProjType() {
        return projType;
    }

    public void setProjType(String projType) {
        this.projType = projType;
    }

    @Basic
    @Column(name = "proj_rank")
    public String getProjRank() {
        return projRank;
    }

    public void setProjRank(String projRank) {
        this.projRank = projRank;
    }

    @Basic
    @Column(name = "rate_unit")
    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    @Basic
    @Column(name = "rate_type")
    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    @Basic
    @Column(name = "max_num")
    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
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
    @Column(name = "sol_score")
    public Integer getSolScore() {
        return solScore;
    }

    public void setSolScore(Integer solScore) {
        this.solScore = solScore;
    }

    @Basic
    @Column(name = "min_mny")
    public Integer getMinMny() {
        return minMny;
    }

    public void setMinMny(Integer minMny) {
        this.minMny = minMny;
    }

    @Basic
    @Column(name = "max_mny")
    public Integer getMaxMny() {
        return maxMny;
    }

    public void setMaxMny(Integer maxMny) {
        this.maxMny = maxMny;
    }

    @Basic
    @Column(name = "mny_base_score")
    public Integer getMnyBaseScore() {
        return mnyBaseScore;
    }

    public void setMnyBaseScore(Integer mnyBaseScore) {
        this.mnyBaseScore = mnyBaseScore;
    }

    @Basic
    @Column(name = "unappr_score")
    public Integer getUnapprScore() {
        return unapprScore;
    }

    public void setUnapprScore(Integer unapprScore) {
        this.unapprScore = unapprScore;
    }

    @Basic
    @Column(name = "unappr_num")
    public Integer getUnapprNum() {
        return unapprNum;
    }

    public void setUnapprNum(Integer unapprNum) {
        this.unapprNum = unapprNum;
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

        Baseproj baseproj = (Baseproj) o;

        if (id != null ? !id.equals(baseproj.id) : baseproj.id != null) return false;
        if (projType != null ? !projType.equals(baseproj.projType) : baseproj.projType != null) return false;
        if (projRank != null ? !projRank.equals(baseproj.projRank) : baseproj.projRank != null) return false;
        if (rateUnit != null ? !rateUnit.equals(baseproj.rateUnit) : baseproj.rateUnit != null) return false;
        if (rateType != null ? !rateType.equals(baseproj.rateType) : baseproj.rateType != null) return false;
        if (maxNum != null ? !maxNum.equals(baseproj.maxNum) : baseproj.maxNum != null) return false;
        if (apprScore != null ? !apprScore.equals(baseproj.apprScore) : baseproj.apprScore != null) return false;
        if (solScore != null ? !solScore.equals(baseproj.solScore) : baseproj.solScore != null) return false;
        if (minMny != null ? !minMny.equals(baseproj.minMny) : baseproj.minMny != null) return false;
        if (maxMny != null ? !maxMny.equals(baseproj.maxMny) : baseproj.maxMny != null) return false;
        if (mnyBaseScore != null ? !mnyBaseScore.equals(baseproj.mnyBaseScore) : baseproj.mnyBaseScore != null)
            return false;
        if (unapprScore != null ? !unapprScore.equals(baseproj.unapprScore) : baseproj.unapprScore != null)
            return false;
        if (unapprNum != null ? !unapprNum.equals(baseproj.unapprNum) : baseproj.unapprNum != null) return false;
        if (memo != null ? !memo.equals(baseproj.memo) : baseproj.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projType != null ? projType.hashCode() : 0);
        result = 31 * result + (projRank != null ? projRank.hashCode() : 0);
        result = 31 * result + (rateUnit != null ? rateUnit.hashCode() : 0);
        result = 31 * result + (rateType != null ? rateType.hashCode() : 0);
        result = 31 * result + (maxNum != null ? maxNum.hashCode() : 0);
        result = 31 * result + (apprScore != null ? apprScore.hashCode() : 0);
        result = 31 * result + (solScore != null ? solScore.hashCode() : 0);
        result = 31 * result + (minMny != null ? minMny.hashCode() : 0);
        result = 31 * result + (maxMny != null ? maxMny.hashCode() : 0);
        result = 31 * result + (mnyBaseScore != null ? mnyBaseScore.hashCode() : 0);
        result = 31 * result + (unapprScore != null ? unapprScore.hashCode() : 0);
        result = 31 * result + (unapprNum != null ? unapprNum.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
