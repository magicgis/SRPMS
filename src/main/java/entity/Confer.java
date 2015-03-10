package entity;

/**
 * DATE:2015/3/11
 * TIME:01:11
 * Created by guofan on 2015/3/11
 * 会议表
 */
public class Confer {
    /* 会议主键 */
	private String conferid;
	/* 会议类型 */
    private String confertype;
	/* 会议名称 */
    private String confernm;
	/* 会议时间 */
    private String confertime;
    /* 会议地址 */
	private String conferaddr;
	public String getConferid() {
		return conferid;
	}
	public void setConferid(String conferid) {
		this.conferid = conferid;
	}
	public String getConfertype() {
		return confertype;
	}
	public void setConfertype(String confertype) {
		this.confertype = confertype;
	}
	public String getConfernm() {
		return confernm;
	}
	public void setConfernm(String confernm) {
		this.confernm = confernm;
	}
	public String getConfertime() {
		return confertime;
	}
	public void setConfertime(String confertime) {
		this.confertime = confertime;
	}
	public String getConferaddr() {
		return conferaddr;
	}
	public void setConferaddr(String conferaddr) {
		this.conferaddr = conferaddr;
	}
}
