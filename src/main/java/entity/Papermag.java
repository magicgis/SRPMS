package entity;

import java.sql.Date;

/**
 * DATE:2015/3/12
 * TIME:14:20
 * Created by guofan on 2015/3/12
 */
public class Papermag {
    private String paperId;
    private String magId;
    private String colType;
    private String volIss;
    private String bgPage;
    private Integer paperScore;
    private Date pubDate;
    private Mag magByMagId;
    private Paper paperByPaperId;

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getMagId() {
        return magId;
    }

    public void setMagId(String magId) {
        this.magId = magId;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public String getVolIss() {
        return volIss;
    }

    public void setVolIss(String volIss) {
        this.volIss = volIss;
    }

    public String getBgPage() {
        return bgPage;
    }

    public void setBgPage(String bgPage) {
        this.bgPage = bgPage;
    }

    public Integer getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(Integer paperScore) {
        this.paperScore = paperScore;
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

        Papermag papermag = (Papermag) o;

        if (bgPage != null ? !bgPage.equals(papermag.bgPage) : papermag.bgPage != null) return false;
        if (colType != null ? !colType.equals(papermag.colType) : papermag.colType != null) return false;
        if (magId != null ? !magId.equals(papermag.magId) : papermag.magId != null) return false;
        if (paperId != null ? !paperId.equals(papermag.paperId) : papermag.paperId != null) return false;
        if (paperScore != null ? !paperScore.equals(papermag.paperScore) : papermag.paperScore != null) return false;
        if (pubDate != null ? !pubDate.equals(papermag.pubDate) : papermag.pubDate != null) return false;
        if (volIss != null ? !volIss.equals(papermag.volIss) : papermag.volIss != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = paperId != null ? paperId.hashCode() : 0;
        result = 31 * result + (magId != null ? magId.hashCode() : 0);
        result = 31 * result + (colType != null ? colType.hashCode() : 0);
        result = 31 * result + (volIss != null ? volIss.hashCode() : 0);
        result = 31 * result + (bgPage != null ? bgPage.hashCode() : 0);
        result = 31 * result + (paperScore != null ? paperScore.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }

    public Mag getMagByMagId() {
        return magByMagId;
    }

    public void setMagByMagId(Mag magByMagId) {
        this.magByMagId = magByMagId;
    }

    public Paper getPaperByPaperId() {
        return paperByPaperId;
    }

    public void setPaperByPaperId(Paper paperByPaperId) {
        this.paperByPaperId = paperByPaperId;
    }
}
