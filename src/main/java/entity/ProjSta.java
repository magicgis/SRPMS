package entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "proj_sta", schema = "", catalog = "srpms")
public class ProjSta {
    private String id;
    private String projRole;
    private BigDecimal peoUnapprScore;
    private BigDecimal peoApprScore;
    private BigDecimal peoAcctScore;
    private BigDecimal peoAwdScore;
    private Staff staffByStaId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "proj_role")
    public String getProjRole() {
        return projRole;
    }

    public void setProjRole(String projRole) {
        this.projRole = projRole;
    }

    @Basic
    @Column(name = "peo_unappr_score")
    public BigDecimal getPeoUnapprScore() {
        return peoUnapprScore;
    }

    public void setPeoUnapprScore(BigDecimal peoUnapprScore) {
        this.peoUnapprScore = peoUnapprScore;
    }

    @Basic
    @Column(name = "peo_appr_score")
    public BigDecimal getPeoApprScore() {
        return peoApprScore;
    }

    public void setPeoApprScore(BigDecimal peoApprScore) {
        this.peoApprScore = peoApprScore;
    }

    @Basic
    @Column(name = "peo_acct_score")
    public BigDecimal getPeoAcctScore() {
        return peoAcctScore;
    }

    public void setPeoAcctScore(BigDecimal peoAcctScore) {
        this.peoAcctScore = peoAcctScore;
    }

    @Basic
    @Column(name = "peo_awd_score")
    public BigDecimal getPeoAwdScore() {
        return peoAwdScore;
    }

    public void setPeoAwdScore(BigDecimal peoAwdScore) {
        this.peoAwdScore = peoAwdScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjSta projSta = (ProjSta) o;

        if (id != null ? !id.equals(projSta.id) : projSta.id != null) return false;
        if (projRole != null ? !projRole.equals(projSta.projRole) : projSta.projRole != null) return false;
        if (peoUnapprScore != null ? !peoUnapprScore.equals(projSta.peoUnapprScore) : projSta.peoUnapprScore != null)
            return false;
        if (peoApprScore != null ? !peoApprScore.equals(projSta.peoApprScore) : projSta.peoApprScore != null)
            return false;
        if (peoAcctScore != null ? !peoAcctScore.equals(projSta.peoAcctScore) : projSta.peoAcctScore != null)
            return false;
        if (peoAwdScore != null ? !peoAwdScore.equals(projSta.peoAwdScore) : projSta.peoAwdScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (projRole != null ? projRole.hashCode() : 0);
        result = 31 * result + (peoUnapprScore != null ? peoUnapprScore.hashCode() : 0);
        result = 31 * result + (peoApprScore != null ? peoApprScore.hashCode() : 0);
        result = 31 * result + (peoAcctScore != null ? peoAcctScore.hashCode() : 0);
        result = 31 * result + (peoAwdScore != null ? peoAwdScore.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sta_id", referencedColumnName = "id")
    public Staff getStaffByStaId() {
        return staffByStaId;
    }

    public void setStaffByStaId(Staff staffByStaId) {
        this.staffByStaId = staffByStaId;
    }
}
