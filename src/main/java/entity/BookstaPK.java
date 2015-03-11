package entity;

import java.io.Serializable;

/**
 * DATE:2015/3/11
 * TIME:23:51
 * Created by guofan on 2015/3/11
 */
public class BookstaPK implements Serializable {
    private String bkId;
    private String staId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookstaPK bookstaPK = (BookstaPK) o;

        if (bkId != null ? !bkId.equals(bookstaPK.bkId) : bookstaPK.bkId != null) return false;
        if (staId != null ? !staId.equals(bookstaPK.staId) : bookstaPK.staId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bkId != null ? bkId.hashCode() : 0;
        result = 31 * result + (staId != null ? staId.hashCode() : 0);
        return result;
    }
}
