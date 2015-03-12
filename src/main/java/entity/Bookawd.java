package entity;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Bookawd {
    private String idbs;
    private Book bookByBkId;
    private Award awardByAwdId;

    public String getIdbs() {
        return idbs;
    }

    public void setIdbs(String idbs) {
        this.idbs = idbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookawd bookawd = (Bookawd) o;

        if (idbs != null ? !idbs.equals(bookawd.idbs) : bookawd.idbs != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idbs != null ? idbs.hashCode() : 0;
    }

    public Book getBookByBkId() {
        return bookByBkId;
    }

    public void setBookByBkId(Book bookByBkId) {
        this.bookByBkId = bookByBkId;
    }

    public Award getAwardByAwdId() {
        return awardByAwdId;
    }

    public void setAwardByAwdId(Award awardByAwdId) {
        this.awardByAwdId = awardByAwdId;
    }
}
