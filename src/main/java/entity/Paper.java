package entity;

/**
 * 论文
 */
public class Paper {
    /* 论文ID */
	private String paperid;
    /* 论文名称 */
	private String papername;
    /* 发表日期 */
	private String pubdate;
    /* TODO Unknown */
	private Unit unit;
	public String getPaperid() {
		return paperid;
	}
	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}
	public String getPapername() {
		return papername;
	}
	public void setPapername(String papername) {
		this.papername = papername;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	
}
