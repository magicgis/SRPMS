package entity;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guofan on 2015/8/12.
 */
@Entity
@Table(name = "project")
public class Project {
    private String id;
    private String code;
    private String name;
    private String type;
    private String rank;
    private String attr;
    private Integer isAppr;
    private String unit;
    private Integer score;
    private String apprDate;
    private String planDate;
    private String realDate;
    private Integer step;
    private String money;
    private String attachment;
    private String process;
    private String arg;
    private Collection<ProjectMoney> projectMoneys;
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
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "rank")
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "attr")
    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    @Basic
    @Column(name = "is_appr")
    public Integer getIsAppr() {
        return isAppr;
    }

    public void setIsAppr(Integer isAppr) {
        this.isAppr = isAppr;
    }

    @Basic
    @Column(name = "unit")
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
    @Column(name = "appr_date")
    public String getApprDate() {
        return apprDate;
    }

    public void setApprDate(String apprDate) {
        this.apprDate = apprDate;
    }

    @Basic
    @Column(name = "plan_date")
    public String getPlanDate() {
        return planDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    @Basic
    @Column(name = "real_date")
    public String getRealDate() {
        return realDate;
    }

    public void setRealDate(String realDate) {
        this.realDate = realDate;
    }

    @Basic
    @Column(name = "step")
    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Basic
    @Column(name = "money")
    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Basic
    @Column(name = "process")
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "attachment")
    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    @Basic
    @Column(name = "arg")
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Transient
    public Map getArgMap() {
        JsonFactory factory = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(factory);
        TypeReference<HashMap<String, Object>> typeRef
                = new TypeReference<HashMap<String, Object>>() {
        };
        try {
            return mapper.readValue(getArg(), typeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setArgMap(Map infoMap) {
        try {
            this.arg = new ObjectMapper().writeValueAsString(infoMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (code != null ? !code.equals(project.code) : project.code != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (type != null ? !type.equals(project.type) : project.type != null) return false;
        if (rank != null ? !rank.equals(project.rank) : project.rank != null) return false;
        if (attr != null ? !attr.equals(project.attr) : project.attr != null) return false;
        if (isAppr != null ? !isAppr.equals(project.isAppr) : project.isAppr != null) return false;
        if (unit != null ? !unit.equals(project.unit) : project.unit != null) return false;
        if (score != null ? !score.equals(project.score) : project.score != null) return false;
        if (apprDate != null ? !apprDate.equals(project.apprDate) : project.apprDate != null) return false;
        if (planDate != null ? !planDate.equals(project.planDate) : project.planDate != null) return false;
        if (realDate != null ? !realDate.equals(project.realDate) : project.realDate != null) return false;
        if (step != null ? !step.equals(project.step) : project.step != null) return false;
        if (money != null ? !money.equals(project.money) : project.money != null) return false;
        if (attachment != null ? !attachment.equals(project.attachment) : project.attachment != null) return false;
        if (process != null ? !process.equals(project.process) : project.process != null) return false;
        if (arg != null ? !arg.equals(project.arg) : project.arg != null) return false;
        if (projectMoneys != null ? !projectMoneys.equals(project.projectMoneys) : project.projectMoneys != null)
            return false;
        if (dept != null ? !dept.equals(project.dept) : project.dept != null) return false;
        return !(standard != null ? !standard.equals(project.standard) : project.standard != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (attr != null ? attr.hashCode() : 0);
        result = 31 * result + (isAppr != null ? isAppr.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (apprDate != null ? apprDate.hashCode() : 0);
        result = 31 * result + (planDate != null ? planDate.hashCode() : 0);
        result = 31 * result + (realDate != null ? realDate.hashCode() : 0);
        result = 31 * result + (step != null ? step.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (attachment != null ? attachment.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (projectMoneys != null ? projectMoneys.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    public Collection<ProjectMoney> getProjectMoneys() {
        return projectMoneys;
    }

    public void setProjectMoneys(Collection<ProjectMoney> projectMoneys) {
        this.projectMoneys = projectMoneys;
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
}
