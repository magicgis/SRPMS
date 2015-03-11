package entity;

import java.io.Serializable;

/**
 * DATE:2015/3/11
 * TIME:23:51
 * Created by guofan on 2015/3/11
 */
public class BookawdPK implements Serializable {
    private String bkId;
    private String awdId;

    public String getBkId() {
        return bkId;
    }

    public void setBkId(String bkId) {
        this.bkId = bkId;
    }

    public String getAwdId() {
        return awdId;
    }

    public void setAwdId(String awdId) {
        this.awdId = awdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookawdPK bookawdPK = (BookawdPK) o;

        if (awdId != null ? !awdId.equals(bookawdPK.awdId) : bookawdPK.awdId != null) return false;
        if (bkId != null ? !bkId.equals(bookawdPK.bkId) : bookawdPK.bkId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bkId != null ? bkId.hashCode() : 0;
        result = 31 * result + (awdId != null ? awdId.hashCode() : 0);
        return result;
    }
}
