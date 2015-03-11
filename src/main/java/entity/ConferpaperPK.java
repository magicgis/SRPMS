package entity;

import java.io.Serializable;

/**
 * DATE:2015/3/11
 * TIME:23:51
 * Created by guofan on 2015/3/11
 */
public class ConferpaperPK implements Serializable {
    private String conferId;
    private String paperId;

    public String getConferId() {
        return conferId;
    }

    public void setConferId(String conferId) {
        this.conferId = conferId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConferpaperPK that = (ConferpaperPK) o;

        if (conferId != null ? !conferId.equals(that.conferId) : that.conferId != null) return false;
        if (paperId != null ? !paperId.equals(that.paperId) : that.paperId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conferId != null ? conferId.hashCode() : 0;
        result = 31 * result + (paperId != null ? paperId.hashCode() : 0);
        return result;
    }
}
