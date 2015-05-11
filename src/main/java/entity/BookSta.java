package entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
@Entity
@Table(name = "book_sta", schema = "", catalog = "srpms")
public class BookSta {
    private String id;
    private BigDecimal peoWdNum;
    private String cbRole;
    private Byte istx;
    private BigDecimal grScore;
    private Book bookByBkId;
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
    @Column(name = "peo_wd_num")
    public BigDecimal getPeoWdNum() {
        return peoWdNum;
    }

    public void setPeoWdNum(BigDecimal peoWdNum) {
        this.peoWdNum = peoWdNum;
    }

    @Basic
    @Column(name = "cb_role")
    public String getCbRole() {
        return cbRole;
    }

    public void setCbRole(String cbRole) {
        this.cbRole = cbRole;
    }

    @Basic
    @Column(name = "istx")
    public Byte getIstx() {
        return istx;
    }

    public void setIstx(Byte istx) {
        this.istx = istx;
    }

    @Basic
    @Column(name = "gr_score")
    public BigDecimal getGrScore() {
        return grScore;
    }

    public void setGrScore(BigDecimal grScore) {
        this.grScore = grScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookSta bookSta = (BookSta) o;

        if (id != null ? !id.equals(bookSta.id) : bookSta.id != null) return false;
        if (peoWdNum != null ? !peoWdNum.equals(bookSta.peoWdNum) : bookSta.peoWdNum != null) return false;
        if (cbRole != null ? !cbRole.equals(bookSta.cbRole) : bookSta.cbRole != null) return false;
        if (istx != null ? !istx.equals(bookSta.istx) : bookSta.istx != null) return false;
        if (grScore != null ? !grScore.equals(bookSta.grScore) : bookSta.grScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (peoWdNum != null ? peoWdNum.hashCode() : 0);
        result = 31 * result + (cbRole != null ? cbRole.hashCode() : 0);
        result = 31 * result + (istx != null ? istx.hashCode() : 0);
        result = 31 * result + (grScore != null ? grScore.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bk_id", referencedColumnName = "id")
    public Book getBookByBkId() {
        return bookByBkId;
    }

    public void setBookByBkId(Book bookByBkId) {
        this.bookByBkId = bookByBkId;
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
