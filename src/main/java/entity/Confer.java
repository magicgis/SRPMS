package entity;

import java.sql.Timestamp;

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
    private String conferId;
    private String conferType;
    private String conferNm;
    private Timestamp conferTime;
    private String conferAddr;

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

    public String getConferId() {
        return conferId;
    }

    public void setConferId(String conferId) {
        this.conferId = conferId;
    }

    public String getConferType() {
        return conferType;
    }

    public void setConferType(String conferType) {
        this.conferType = conferType;
    }

    public String getConferNm() {
        return conferNm;
    }

    public void setConferNm(String conferNm) {
        this.conferNm = conferNm;
    }

    public Timestamp getConferTime() {
        return conferTime;
    }

    public void setConferTime(Timestamp conferTime) {
        this.conferTime = conferTime;
    }

    public String getConferAddr() {
        return conferAddr;
    }

    public void setConferAddr(String conferAddr) {
        this.conferAddr = conferAddr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Confer confer = (Confer) o;

        if (conferAddr != null ? !conferAddr.equals(confer.conferAddr) : confer.conferAddr != null) return false;
        if (conferId != null ? !conferId.equals(confer.conferId) : confer.conferId != null) return false;
        if (conferNm != null ? !conferNm.equals(confer.conferNm) : confer.conferNm != null) return false;
        if (conferTime != null ? !conferTime.equals(confer.conferTime) : confer.conferTime != null) return false;
        if (conferType != null ? !conferType.equals(confer.conferType) : confer.conferType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conferId != null ? conferId.hashCode() : 0;
        result = 31 * result + (conferType != null ? conferType.hashCode() : 0);
        result = 31 * result + (conferNm != null ? conferNm.hashCode() : 0);
        result = 31 * result + (conferTime != null ? conferTime.hashCode() : 0);
        result = 31 * result + (conferAddr != null ? conferAddr.hashCode() : 0);
        return result;
    }
}
