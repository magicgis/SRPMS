package entity;

import java.io.Serializable;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class PapermagPK implements Serializable {
    private String paperId;
    private String magId;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getMagId() {
        return magId;
    }

    public void setMagId(String magId) {
        this.magId = magId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PapermagPK that = (PapermagPK) o;

        if (magId != null ? !magId.equals(that.magId) : that.magId != null) return false;
        if (paperId != null ? !paperId.equals(that.paperId) : that.paperId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (magId != null ? magId.hashCode() : 0);
        return result;
    }
}
