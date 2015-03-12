package entity;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Bookawd {
    private String bkId;
    private String awdId;
    private Award awardByAwdId;
    private Book bookByBkId;

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

        Bookawd bookawd = (Bookawd) o;

        if (awdId != null ? !awdId.equals(bookawd.awdId) : bookawd.awdId != null) return false;
        if (bkId != null ? !bkId.equals(bookawd.bkId) : bookawd.bkId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bkId != null ? bkId.hashCode() : 0;
        result = 31 * result + (awdId != null ? awdId.hashCode() : 0);
        return result;
    }

    public Award getAwardByAwdId() {
        return awardByAwdId;
    }

    public void setAwardByAwdId(Award awardByAwdId) {
        this.awardByAwdId = awardByAwdId;
    }

    public Book getBookByBkId() {
        return bookByBkId;
    }

    public void setBookByBkId(Book bookByBkId) {
        this.bookByBkId = bookByBkId;
    }
}
