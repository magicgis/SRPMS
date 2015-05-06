package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "proj_money", schema = "", catalog = "srpms")
public class ProjMoney {
    private String id;
    private Date toAcctTime;
    private BigDecimal toAcctMny;
    private BigDecimal outMny;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "to_acct_time")
    public Date getToAcctTime() {
        return toAcctTime;
    }

    public void setToAcctTime(Date toAcctTime) {
        this.toAcctTime = toAcctTime;
    }

    @Basic
    @Column(name = "to_acct_mny")
    public BigDecimal getToAcctMny() {
        return toAcctMny;
    }

    public void setToAcctMny(BigDecimal toAcctMny) {
        this.toAcctMny = toAcctMny;
    }

    @Basic
    @Column(name = "out_mny")
    public BigDecimal getOutMny() {
        return outMny;
    }

    public void setOutMny(BigDecimal outMny) {
        this.outMny = outMny;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjMoney projMoney = (ProjMoney) o;

        if (id != null ? !id.equals(projMoney.id) : projMoney.id != null) return false;
        if (toAcctTime != null ? !toAcctTime.equals(projMoney.toAcctTime) : projMoney.toAcctTime != null) return false;
        if (toAcctMny != null ? !toAcctMny.equals(projMoney.toAcctMny) : projMoney.toAcctMny != null) return false;
        if (outMny != null ? !outMny.equals(projMoney.outMny) : projMoney.outMny != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (toAcctTime != null ? toAcctTime.hashCode() : 0);
        result = 31 * result + (toAcctMny != null ? toAcctMny.hashCode() : 0);
        result = 31 * result + (outMny != null ? outMny.hashCode() : 0);
        return result;
    }
}
