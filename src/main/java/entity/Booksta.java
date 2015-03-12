package entity;

import java.math.BigDecimal;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Booksta {
    private String bkId;
    private String staId;
    private BigDecimal peoWdNum;
    private String bkcbRole;
    private Byte isTx;
    private BigDecimal bkScore;
    private Book bookByBkId;
    private Staff staffByStaId;

    public String getBkId() {
        return bkId;
    }

    public void setBkId(String bkId) {
        this.bkId = bkId;
    }

    public String getStaId() {
        return staId;
    }

    public void setStaId(String staId) {
        this.staId = staId;
    }

    public BigDecimal getPeoWdNum() {
        return peoWdNum;
    }

    public void setPeoWdNum(BigDecimal peoWdNum) {
        this.peoWdNum = peoWdNum;
    }

    public String getBkcbRole() {
        return bkcbRole;
    }

    public void setBkcbRole(String bkcbRole) {
        this.bkcbRole = bkcbRole;
    }

    public Byte getIsTx() {
        return isTx;
    }

    public void setIsTx(Byte isTx) {
        this.isTx = isTx;
    }

    public BigDecimal getBkScore() {
        return bkScore;
    }

    public void setBkScore(BigDecimal bkScore) {
        this.bkScore = bkScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booksta booksta = (Booksta) o;

        if (bkId != null ? !bkId.equals(booksta.bkId) : booksta.bkId != null) return false;
        if (bkScore != null ? !bkScore.equals(booksta.bkScore) : booksta.bkScore != null) return false;
        if (bkcbRole != null ? !bkcbRole.equals(booksta.bkcbRole) : booksta.bkcbRole != null) return false;
        if (isTx != null ? !isTx.equals(booksta.isTx) : booksta.isTx != null) return false;
        if (peoWdNum != null ? !peoWdNum.equals(booksta.peoWdNum) : booksta.peoWdNum != null) return false;
        if (staId != null ? !staId.equals(booksta.staId) : booksta.staId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bkId != null ? bkId.hashCode() : 0;
        result = 31 * result + (staId != null ? staId.hashCode() : 0);
        result = 31 * result + (peoWdNum != null ? peoWdNum.hashCode() : 0);
        result = 31 * result + (bkcbRole != null ? bkcbRole.hashCode() : 0);
        result = 31 * result + (isTx != null ? isTx.hashCode() : 0);
        result = 31 * result + (bkScore != null ? bkScore.hashCode() : 0);
        return result;
    }

    public Book getBookByBkId() {
        return bookByBkId;
    }

    public void setBookByBkId(Book bookByBkId) {
        this.bookByBkId = bookByBkId;
    }

    public Staff getStaffByStaId() {
        return staffByStaId;
    }

    public void setStaffByStaId(Staff staffByStaId) {
        this.staffByStaId = staffByStaId;
    }
}
