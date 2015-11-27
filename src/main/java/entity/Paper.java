package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

import static util.Trans.argMap;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "paper")
public class Paper implements VirtualEntity {
    private String id;
    private String type;
    private String name;
    private String vol;
    private String iss;
    private String bgPage;
    private String pubDate;
    private BigDecimal score;
    private Integer numWord;
    private String arg;
    private String attachment;
    private Mag mag;
    private Confer confer;
    private Newspaper newspaper;
    private BaseInfo dept;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //todo
    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
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
    @Column(name = "arg", nullable = true, insertable = true, updatable = true, length = 3000)
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Transient
    public Map getArgMap() {
        return argMap(this.arg);
    }

    public void setArgMap(Map argMap) {
        try {
            Map<String, Object> map = null;
            try {
                map = new ObjectMapper().convertValue(this, Map.class);
                map.remove("arg");
                map.remove("argMap");
            } catch (Exception x) {
                x.printStackTrace();
            }
            if (map != null) {
                argMap.put("View", map);
            }
            String x = new ObjectMapper().writeValueAsString(argMap);
            System.out.println(x.length());
            this.arg = null;
            this.arg = x;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
        if (type != null ? !type.equals(paper.type) : paper.type != null) return false;
        if (name != null ? !name.equals(paper.name) : paper.name != null) return false;
        if (vol != null ? !vol.equals(paper.vol) : paper.vol != null) return false;
        if (iss != null ? !iss.equals(paper.iss) : paper.iss != null) return false;
        if (bgPage != null ? !bgPage.equals(paper.bgPage) : paper.bgPage != null) return false;
        if (pubDate != null ? !pubDate.equals(paper.pubDate) : paper.pubDate != null) return false;
        if (score != null ? !score.equals(paper.score) : paper.score != null) return false;
        if (numWord != null ? !numWord.equals(paper.numWord) : paper.numWord != null) return false;
        if (arg != null ? !arg.equals(paper.arg) : paper.arg != null) return false;
        if (attachment != null ? !attachment.equals(paper.attachment) : paper.attachment != null) return false;
        if (mag != null ? !mag.equals(paper.mag) : paper.mag != null) return false;
        if (confer != null ? !confer.equals(paper.confer) : paper.confer != null) return false;
        if (newspaper != null ? !newspaper.equals(paper.newspaper) : paper.newspaper != null) return false;
        return !(dept != null ? !dept.equals(paper.dept) : paper.dept != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vol != null ? vol.hashCode() : 0);
        result = 31 * result + (iss != null ? iss.hashCode() : 0);
        result = 31 * result + (bgPage != null ? bgPage.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (numWord != null ? numWord.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        result = 31 * result + (mag != null ? mag.hashCode() : 0);
        result = 31 * result + (confer != null ? confer.hashCode() : 0);
        result = 31 * result + (newspaper != null ? newspaper.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "mag", referencedColumnName = "id")
    public Mag getMag() {
        return mag;
    }

    public void setMag(Mag mag) {
        this.mag = mag;
    }

    @ManyToOne
    @JoinColumn(name = "confer", referencedColumnName = "id")
    public Confer getConfer() {
        return confer;
    }

    public void setConfer(Confer confer) {
        this.confer = confer;
    }

    @ManyToOne
    @JoinColumn(name = "newspaper", referencedColumnName = "id")
    public Newspaper getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(Newspaper newspaper) {
        this.newspaper = newspaper;
    }

    @ManyToOne
    @JoinColumn(name = "dept", referencedColumnName = "id")
    public BaseInfo getDept() {
        return dept;
    }

    public void setDept(BaseInfo dept) {
        this.dept = dept;
    }

    @Override
    @Transient
    @Deprecated
    public Standard getStandard() {
        return null;
    }

    @Override
    public void setStandard(Standard standard) {

    }

    @Override
    @Transient
    @Deprecated
    public String getProcess() {
        return null;
    }

    @Override
    public void setProcess(String process) {

    }
}
