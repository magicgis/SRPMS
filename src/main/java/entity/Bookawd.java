package entity;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Bookawd {
    private String idba;
    private Book bookByBkId;
    private Award awardByAwdId;

    public String getIdba() {
        return idba;
    }

    public void setIdba(String idbs) {
        this.idba = idbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookawd bookawd = (Bookawd) o;

        return !(idba != null ? !idba.equals(bookawd.idba) : bookawd.idba != null);

    }

    @Override
    public int hashCode() {
        return idba != null ? idba.hashCode() : 0;
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
