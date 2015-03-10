package entity;

/**
 * 会议文章
 */
public class Conferpaper {
    /* 会议外键 */
	private String conferid;
    /* 论文外键 */
	private String paperid;
    /* cps分数 */
	private String cpscore;

	public String getConferid() {
		return conferid;
	}
	public void setConferid(String conferid) {
		this.conferid = conferid;
	}
	public String getPaperid() {
		return paperid;
	}
	public void setPaperid(String paperid) {
		this.paperid = paperid;
	}
	public String getCpscore() {
		return cpscore;
	}
	public void setCpscore(String cpscore) {
		this.cpscore = cpscore;
	}
}
