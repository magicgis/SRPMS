package entity;

public class Mag {

	private String magid;
	private String magname;
	private String magsnm;
	private Grade grade;
	private String issn;
	private String magsub;
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
