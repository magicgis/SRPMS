package entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * DATE:2015/3/13
 * TIME:2:23
 * Created by guofan on 2015/3/13
 */
public class Conferpaper {
    private String idpc;
    private BigDecimal cpScore;
    private Date pubDate;
    private Confer conferByConferId;
    private Paper paperByPaperId;

    public String getIdpc() {
        return idpc;
    }

    public void setIdpc(String idpc) {
        this.idpc = idpc;
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

        return !(cpScore != null ? !cpScore.equals(that.cpScore) : that.cpScore != null) && !(idpc != null ? !idpc.equals(that.idpc) : that.idpc != null) && !(pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null);

    }

    @Override
    public int hashCode() {
        int result = idpc != null ? idpc.hashCode() : 0;
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
