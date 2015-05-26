package entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "project", schema = "", catalog = "srpms")
public class Project {
    private String id;

    @Id
    @javax.persistence.Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String projCode;

    @Basic
    @javax.persistence.Column(name = "proj_code")
    public String getProjCode() {
        return projCode;
    }

    public void setProjCode(String projCode) {
        this.projCode = projCode;
    }

    private String name;

    @Basic
    @javax.persistence.Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;

    @Basic
    @javax.persistence.Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String projRank;

    @Basic
    @javax.persistence.Column(name = "proj_rank")
    public String getProjRank() {
        return projRank;
    }

    public void setProjRank(String projRank) {
        this.projRank = projRank;
    }

    private String rateUnit;

    @Basic
    @javax.persistence.Column(name = "rate_unit")
    public String getRateUnit() {
        return rateUnit;
    }

    public void setRateUnit(String rateUnit) {
        this.rateUnit = rateUnit;
    }

    private String rateType;

    @Basic
    @javax.persistence.Column(name = "rate_type")
    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    private Byte isappr;

    @Basic
    @javax.persistence.Column(name = "isappr")
    public Byte getIsappr() {
        return isappr;
    }

    public void setIsappr(Byte isappr) {
        this.isappr = isappr;
    }

    private Integer unapprScore;

    @Basic
    @javax.persistence.Column(name = "unappr_score")
    public Integer getUnapprScore() {
        return unapprScore;
    }

    public void setUnapprScore(Integer unapprScore) {
        this.unapprScore = unapprScore;
    }

    private Date projApprTime;

    @Basic
    @javax.persistence.Column(name = "proj_appr_time")
    public Date getProjApprTime() {
        return projApprTime;
    }

    public void setProjApprTime(Date projApprTime) {
        this.projApprTime = projApprTime;
    }

    private BigDecimal projApprMny;

    @Basic
    @javax.persistence.Column(name = "proj_appr_mny")
    public BigDecimal getProjApprMny() {
        return projApprMny;
    }

    public void setProjApprMny(BigDecimal projApprMny) {
        this.projApprMny = projApprMny;
    }

    private Date planSolTime;

    @Basic
    @javax.persistence.Column(name = "plan_sol_time")
    public Date getPlanSolTime() {
        return planSolTime;
    }

    public void setPlanSolTime(Date planSolTime) {
        this.planSolTime = planSolTime;
    }

    private Date actSolTime;

    @Basic
    @javax.persistence.Column(name = "act_sol_time")
    public Date getActSolTime() {
        return actSolTime;
    }

    public void setActSolTime(Date actSolTime) {
        this.actSolTime = actSolTime;
    }

    private BigDecimal planOutMny;

    @Basic
    @javax.persistence.Column(name = "plan_out_mny")
    public BigDecimal getPlanOutMny() {
        return planOutMny;
    }

    public void setPlanOutMny(BigDecimal planOutMny) {
        this.planOutMny = planOutMny;
    }

    private String projAttr;

    @Basic
    @javax.persistence.Column(name = "proj_attr")
    public String getProjAttr() {
        return projAttr;
    }

    public void setProjAttr(String projAttr) {
        this.projAttr = projAttr;
    }

    private Byte isSubProj;

    @Basic
    @javax.persistence.Column(name = "is_sub_proj")
    public Byte getIsSubProj() {
        return isSubProj;
    }

    public void setIsSubProj(Byte isSubProj) {
        this.isSubProj = isSubProj;
    }

    private Byte isUnionProj;

    @Basic
    @javax.persistence.Column(name = "is_union_proj")
    public Byte getIsUnionProj() {
        return isUnionProj;
    }

    public void setIsUnionProj(Byte isUnionProj) {
        this.isUnionProj = isUnionProj;
    }

    private Integer projApprScore;

    @Basic
    @javax.persistence.Column(name = "proj_appr_score")
    public Integer getProjApprScore() {
        return projApprScore;
    }

    public void setProjApprScore(Integer projApprScore) {
        this.projApprScore = projApprScore;
    }

    private Integer toAcctScore;

    @Basic
    @javax.persistence.Column(name = "to_acct_score")
    public Integer getToAcctScore() {
        return toAcctScore;
    }

    public void setToAcctScore(Integer toAcctScore) {
        this.toAcctScore = toAcctScore;
    }

    private Integer projSolScore;

    @Basic
    @javax.persistence.Column(name = "proj_sol_score")
    public Integer getProjSolScore() {
        return projSolScore;
    }

    public void setProjSolScore(Integer projSolScore) {
        this.projSolScore = projSolScore;
    }

    private String awdProp;

    @Basic
    @javax.persistence.Column(name = "awd_prop")
    public String getAwdProp() {
        return awdProp;
    }

    public void setAwdProp(String awdProp) {
        this.awdProp = awdProp;
    }

    private String awdType;

    @Basic
    @javax.persistence.Column(name = "awd_type")
    public String getAwdType() {
        return awdType;
    }

    public void setAwdType(String awdType) {
        this.awdType = awdType;
    }

    private String awdRank;

    @Basic
    @javax.persistence.Column(name = "awd_rank")
    public String getAwdRank() {
        return awdRank;
    }

    public void setAwdRank(String awdRank) {
        this.awdRank = awdRank;
    }

    private Integer awdScore;

    @Basic
    @javax.persistence.Column(name = "awd_score")
    public Integer getAwdScore() {
        return awdScore;
    }

    public void setAwdScore(Integer awdScore) {
        this.awdScore = awdScore;
    }

    private String awdYear;

    @Basic
    @javax.persistence.Column(name = "awd_year")
    public String getAwdYear() {
        return awdYear;
    }

    public void setAwdYear(String awdYear) {
        this.awdYear = awdYear;
    }

    private String memo;

    @Basic
    @javax.persistence.Column(name = "memo")
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

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (projCode != null ? !projCode.equals(project.projCode) : project.projCode != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (type != null ? !type.equals(project.type) : project.type != null) return false;
        if (projRank != null ? !projRank.equals(project.projRank) : project.projRank != null) return false;
        if (rateUnit != null ? !rateUnit.equals(project.rateUnit) : project.rateUnit != null) return false;
        if (rateType != null ? !rateType.equals(project.rateType) : project.rateType != null) return false;
        if (isappr != null ? !isappr.equals(project.isappr) : project.isappr != null) return false;
        if (unapprScore != null ? !unapprScore.equals(project.unapprScore) : project.unapprScore != null) return false;
        if (projApprTime != null ? !projApprTime.equals(project.projApprTime) : project.projApprTime != null)
            return false;
        if (projApprMny != null ? !projApprMny.equals(project.projApprMny) : project.projApprMny != null) return false;
        if (planSolTime != null ? !planSolTime.equals(project.planSolTime) : project.planSolTime != null) return false;
        if (actSolTime != null ? !actSolTime.equals(project.actSolTime) : project.actSolTime != null) return false;
        if (planOutMny != null ? !planOutMny.equals(project.planOutMny) : project.planOutMny != null) return false;
        if (projAttr != null ? !projAttr.equals(project.projAttr) : project.projAttr != null) return false;
        if (isSubProj != null ? !isSubProj.equals(project.isSubProj) : project.isSubProj != null) return false;
        if (isUnionProj != null ? !isUnionProj.equals(project.isUnionProj) : project.isUnionProj != null) return false;
        if (projApprScore != null ? !projApprScore.equals(project.projApprScore) : project.projApprScore != null)
            return false;
        if (toAcctScore != null ? !toAcctScore.equals(project.toAcctScore) : project.toAcctScore != null) return false;
        if (projSolScore != null ? !projSolScore.equals(project.projSolScore) : project.projSolScore != null)
            return false;
        if (awdProp != null ? !awdProp.equals(project.awdProp) : project.awdProp != null) return false;
        if (awdType != null ? !awdType.equals(project.awdType) : project.awdType != null) return false;
        if (awdRank != null ? !awdRank.equals(project.awdRank) : project.awdRank != null) return false;
        if (awdScore != null ? !awdScore.equals(project.awdScore) : project.awdScore != null) return false;
        if (awdYear != null ? !awdYear.equals(project.awdYear) : project.awdYear != null) return false;
        if (memo != null ? !memo.equals(project.memo) : project.memo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projCode != null ? projCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (projRank != null ? projRank.hashCode() : 0);
        result = 31 * result + (rateUnit != null ? rateUnit.hashCode() : 0);
        result = 31 * result + (rateType != null ? rateType.hashCode() : 0);
        result = 31 * result + (isappr != null ? isappr.hashCode() : 0);
        result = 31 * result + (unapprScore != null ? unapprScore.hashCode() : 0);
        result = 31 * result + (projApprTime != null ? projApprTime.hashCode() : 0);
        result = 31 * result + (projApprMny != null ? projApprMny.hashCode() : 0);
        result = 31 * result + (planSolTime != null ? planSolTime.hashCode() : 0);
        result = 31 * result + (actSolTime != null ? actSolTime.hashCode() : 0);
        result = 31 * result + (planOutMny != null ? planOutMny.hashCode() : 0);
        result = 31 * result + (projAttr != null ? projAttr.hashCode() : 0);
        result = 31 * result + (isSubProj != null ? isSubProj.hashCode() : 0);
        result = 31 * result + (isUnionProj != null ? isUnionProj.hashCode() : 0);
        result = 31 * result + (projApprScore != null ? projApprScore.hashCode() : 0);
        result = 31 * result + (toAcctScore != null ? toAcctScore.hashCode() : 0);
        result = 31 * result + (projSolScore != null ? projSolScore.hashCode() : 0);
        result = 31 * result + (awdProp != null ? awdProp.hashCode() : 0);
        result = 31 * result + (awdType != null ? awdType.hashCode() : 0);
        result = 31 * result + (awdRank != null ? awdRank.hashCode() : 0);
        result = 31 * result + (awdScore != null ? awdScore.hashCode() : 0);
        result = 31 * result + (awdYear != null ? awdYear.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        return result;
    }
}
