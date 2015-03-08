package entity;

import entity.Dept;
import entity.Rank;

public class Staff {

	private String staid;
	private String staname;
	private Dept dept;
	private String idcard;
	private Rank rank;
	private String edu;
	private String position;
	private String degree;
	private String stasnm;
	public String getStaid() {
		return staid;
	}
	public void setStaid(String staid) {
		this.staid = staid;
	}
	public String getStaname() {
		return staname;
	}
	public void setStaname(String staname) {
		this.staname = staname;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getStasnm() {
		return stasnm;
	}
	public void setStasnm(String stasnm) {
		this.stasnm = stasnm;
	}
	public Rank getRank() {
		return rank;
	}
	public void setRank(Rank rank) {
		this.rank = rank;
	}
}
