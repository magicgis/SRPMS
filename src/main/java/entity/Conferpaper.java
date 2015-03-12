package entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Conferpaper {
    private String conferId;
    private String paperId;
    private BigDecimal cpScore;
    private Date pubDate;
    private Confer conferByConferId;
    private Paper paperByPaperId;

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

    public Confer getConferByConferId() {
        return conferByConferId;
    }

    public void setConferByConferId(Confer conferByConferId) {
        this.conferByConferId = conferByConferId;
    }

    public Paper getPaperByPaperId() {
        return paperByPaperId;
    }

    public void setPaperByPaperId(Paper paperByPaperId) {
        this.paperByPaperId = paperByPaperId;
    }
}
