package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Map;

import static util.Trans.argMap;

/**
 * Created by guofan on 2015/10/2.
 */
@Entity
@Table(name = "patent")
public class Patent implements VirtualEntity {
    private String id;
    private String name;
    private String patentPubNo;
    private String patentNo;
    private String pubDate;
    private String apprDate;
    private String endfillDate;
    private String state;
    private Integer score;
    private String arg;
    private String process;
    private BaseInfo dept;
    private Standard standard;

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

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "patent_pub_no")
    public String getPatentPubNo() {
        return patentPubNo;
    }

    public void setPatentPubNo(String patentPubNo) {
        this.patentPubNo = patentPubNo;
    }

    @Basic
    @Column(name = "patent_no")
    public String getPatentNo() {
        return patentNo;
    }

    public void setPatentNo(String patentNo) {
        this.patentNo = patentNo;
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
    @Column(name = "appr_date")
    public String getApprDate() {
        return apprDate;
    }

    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    @Basic
    @Column(name = "endfill_date")
    public String getEndfillDate() {
        return endfillDate;
    }

    public void setEndfillDate(String endfillDate) {
        this.endfillDate = endfillDate;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
    @Column(name = "process")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @ManyToOne
    @JoinColumn(name = "dept", referencedColumnName = "id")
    public BaseInfo getDept() {
        return dept;
    }

    public void setDept(BaseInfo dept) {
        this.dept = dept;
    }

    @ManyToOne
    @JoinColumn(name = "standard", referencedColumnName = "id")
    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patent patent = (Patent) o;

        if (id != null ? !id.equals(patent.id) : patent.id != null) return false;
        if (name != null ? !name.equals(patent.name) : patent.name != null) return false;
        if (patentPubNo != null ? !patentPubNo.equals(patent.patentPubNo) : patent.patentPubNo != null) return false;
        if (patentNo != null ? !patentNo.equals(patent.patentNo) : patent.patentNo != null) return false;
        if (pubDate != null ? !pubDate.equals(patent.pubDate) : patent.pubDate != null) return false;
        if (apprDate != null ? !apprDate.equals(patent.apprDate) : patent.apprDate != null) return false;
        if (endfillDate != null ? !endfillDate.equals(patent.endfillDate) : patent.endfillDate != null) return false;
        if (state != null ? !state.equals(patent.state) : patent.state != null) return false;
        if (score != null ? !score.equals(patent.score) : patent.score != null) return false;
        if (arg != null ? !arg.equals(patent.arg) : patent.arg != null) return false;
        if (process != null ? !process.equals(patent.process) : patent.process != null) return false;
        if (dept != null ? !dept.equals(patent.dept) : patent.dept != null) return false;
        return !(standard != null ? !standard.equals(patent.standard) : patent.standard != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (patentPubNo != null ? patentPubNo.hashCode() : 0);
        result = 31 * result + (patentNo != null ? patentNo.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (apprDate != null ? apprDate.hashCode() : 0);
        result = 31 * result + (endfillDate != null ? endfillDate.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
        return result;
    }
}
