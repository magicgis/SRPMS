package entity;

import java.io.Serializable;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class PaperstaPK implements Serializable {
    private String paperId;
    private String staId;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
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

        PaperstaPK that = (PaperstaPK) o;

        if (paperId != null ? !paperId.equals(that.paperId) : that.paperId != null) return false;
        if (staId != null ? !staId.equals(that.staId) : that.staId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (staId != null ? staId.hashCode() : 0);
        return result;
    }
}
