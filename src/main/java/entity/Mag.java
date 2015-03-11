package entity;

/**
 * 期刊表
 */
public class Mag {
    /* 期刊ID */
	private String magid;
    /* 期刊名称 */
	private String magname;
    /* 期刊期数 */
	private String magsnm;
    /* 期刊等级 */
	private Grade grade;
    /* ISSN */
	private String issn;
    /* 期刊出版社 */
	private String magsub;
    /* TODO Unknown*/
	private String fq;
    private String magId;
    private String magName;
    private String magSNm;
    private String cn;
    private String magSub;

	public String getMagid() {
		return magid;
	}

	public void setMagid(String magid) {
		this.magid = magid;
	}

	public String getMagname() {
		return magname;
	}

	public void setMagname(String magname) {
		this.magname = magname;
	}

	public String getMagsnm() {
		return magsnm;
	}

	public void setMagsnm(String magsnm) {
		this.magsnm = magsnm;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getMagsub() {
		return magsub;
	}

	public void setMagsub(String magsub) {
		this.magsub = magsub;
	}

	public String getFq() {
		return fq;
	}

	public void setFq(String fq) {
		this.fq = fq;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

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
}
