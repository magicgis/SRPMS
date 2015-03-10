package entity;

/**
 * 期刊论文
 */
public class Papermag {
    /* 论文ID */
	private String paperid;
    /* 期刊ID */
	private String magid;
    /* 收录类型 */
	private String coltype;
    /* 卷期页 */
	private String voliss;
    /* 发表起止页码 */
	private String bgpage;
    /* 得分 */
	private String paperscore;
	public String getPaperid() {
		return paperid;
	}
	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}
	public String getMagid() {
		return magid;
	}
	public void setMagid(String magid) {
		this.magid = magid;
	}
	public String getColtype() {
		return coltype;
	}
	public void setColtype(String coltype) {
		this.coltype = coltype;
	}
	public String getVoliss() {
		return voliss;
	}
	public void setVoliss(String voliss) {
		this.voliss = voliss;
	}
	public String getBgpage() {
		return bgpage;
	}
	public void setBgpage(String bgpage) {
		this.bgpage = bgpage;
	}
	public String getPaperscore() {
		return paperscore;
	}
	public void setPaperscore(String paperscore) {
		this.paperscore = paperscore;
	}

}
