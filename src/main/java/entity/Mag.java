package entity;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Mag {
    private String magId;
    private String magName;
    private String magSNm;
    private String issn;
    private String cn;
    private String magSub;
    private String fq;
    private Base baseByGradeId;

    public String getMagId() {
        return magId;
    }

    public void setMagId(String magId) {
        this.magId = magId;
    }

    public String getMagName() {
        return magName;
    }

    public void setMagName(String magName) {
        this.magName = magName;
    }

    public String getMagSNm() {
        return magSNm;
    }

    public void setMagSNm(String magSNm) {
        this.magSNm = magSNm;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getMagSub() {
        return magSub;
    }

    public void setMagSub(String magSub) {
        this.magSub = magSub;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mag mag = (Mag) o;

        if (cn != null ? !cn.equals(mag.cn) : mag.cn != null) return false;
        if (fq != null ? !fq.equals(mag.fq) : mag.fq != null) return false;
        if (issn != null ? !issn.equals(mag.issn) : mag.issn != null) return false;
        if (magId != null ? !magId.equals(mag.magId) : mag.magId != null) return false;
        if (magName != null ? !magName.equals(mag.magName) : mag.magName != null) return false;
        if (magSNm != null ? !magSNm.equals(mag.magSNm) : mag.magSNm != null) return false;
        if (magSub != null ? !magSub.equals(mag.magSub) : mag.magSub != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = magId != null ? magId.hashCode() : 0;
        result = 31 * result + (magName != null ? magName.hashCode() : 0);
        result = 31 * result + (magSNm != null ? magSNm.hashCode() : 0);
        result = 31 * result + (issn != null ? issn.hashCode() : 0);
        result = 31 * result + (cn != null ? cn.hashCode() : 0);
        result = 31 * result + (magSub != null ? magSub.hashCode() : 0);
        result = 31 * result + (fq != null ? fq.hashCode() : 0);
        return result;
    }

    public Base getBaseByGradeId() {
        return baseByGradeId;
    }

    public void setBaseByGradeId(Base baseByGradeId) {
        this.baseByGradeId = baseByGradeId;
    }
}
