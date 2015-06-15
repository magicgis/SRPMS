package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "paper", schema = "", catalog = "srpms")
public class Paper {
    private String id;
    private Byte paperType;
    private String name;
    private String vol;
    private String iss;
    private String bgPage;
    private String pubDate;
    private Integer score;
    private Integer numWord;
    private String memo;
    private String attachment;
    private Mag pMag;
    private Confer pConfer;
    private Newspaper pNewspaper;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "paper_type")
    public Byte getPaperType() {
        return paperType;
    }

    public void setPaperType(Byte paperType) {
        this.paperType = paperType;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "vol")
    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    @Basic
    @Column(name = "iss")
    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    @Basic
    @Column(name = "bg_page")
    public String getBgPage() {
        return bgPage;
    }

    public void setBgPage(String bgPage) {
        this.bgPage = bgPage;
    }

    @Basic
    @Column(name = "pub_date")
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "num_word")
    public Integer getNumWord() {
        return numWord;
    }

    public void setNumWord(Integer numWord) {
        this.numWord = numWord;
    }

    @Basic
    @Column(name = "memo")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "attachment")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paper paper = (Paper) o;

        if (id != null ? !id.equals(paper.id) : paper.id != null) return false;
        if (paperType != null ? !paperType.equals(paper.paperType) : paper.paperType != null) return false;
        if (name != null ? !name.equals(paper.name) : paper.name != null) return false;
        if (vol != null ? !vol.equals(paper.vol) : paper.vol != null) return false;
        if (iss != null ? !iss.equals(paper.iss) : paper.iss != null) return false;
        if (bgPage != null ? !bgPage.equals(paper.bgPage) : paper.bgPage != null) return false;
        if (pubDate != null ? !pubDate.equals(paper.pubDate) : paper.pubDate != null) return false;
        if (score != null ? !score.equals(paper.score) : paper.score != null) return false;
        if (numWord != null ? !numWord.equals(paper.numWord) : paper.numWord != null) return false;
        if (memo != null ? !memo.equals(paper.memo) : paper.memo != null) return false;
        if (attachment != null ? !attachment.equals(paper.attachment) : paper.attachment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (paperType != null ? paperType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vol != null ? vol.hashCode() : 0);
        result = 31 * result + (iss != null ? iss.hashCode() : 0);
        result = 31 * result + (bgPage != null ? bgPage.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (numWord != null ? numWord.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "mag", referencedColumnName = "id")
    public Mag getPMag() {
        return pMag;
    }

    public void setPMag(Mag magByMag) {
        this.pMag = magByMag;
    }

    @ManyToOne
    @JoinColumn(name = "confer", referencedColumnName = "id")
    public Confer getPConfer() {
        return pConfer;
    }

    public void setPConfer(Confer conferByConfer) {
        this.pConfer = conferByConfer;
    }

    @ManyToOne
    @JoinColumn(name = "newspaper", referencedColumnName = "id")
    public Newspaper getPNewspaper() {
        return pNewspaper;
    }

    public void setPNewspaper(Newspaper pNewspaper) {
        this.pNewspaper = pNewspaper;
    }
}
