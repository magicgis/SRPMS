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
}
