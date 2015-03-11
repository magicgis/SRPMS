package entity;

import java.math.BigDecimal;
import java.sql.Date;

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
    private String conferId;
    private String paperId;
    private BigDecimal cpScore;
    private Date pubDate;

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

    public String getConferId() {
        return conferId;
    }

    public void setConferId(String conferId) {
        this.conferId = conferId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public BigDecimal getCpScore() {
        return cpScore;
    }

    public void setCpScore(BigDecimal cpScore) {
        this.cpScore = cpScore;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conferpaper that = (Conferpaper) o;

        if (conferId != null ? !conferId.equals(that.conferId) : that.conferId != null) return false;
        if (cpScore != null ? !cpScore.equals(that.cpScore) : that.cpScore != null) return false;
        if (paperId != null ? !paperId.equals(that.paperId) : that.paperId != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = conferId != null ? conferId.hashCode() : 0;
        result = 31 * result + (paperId != null ? paperId.hashCode() : 0);
        result = 31 * result + (cpScore != null ? cpScore.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }
}
