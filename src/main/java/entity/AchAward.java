package entity;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guofan on 2015/10/12.
 */
@Entity
@Table(name = "ach_award", schema = "", catalog = "srpms")
public class AchAward {
    private String id;
    private String name;
    private String date;
    private String achType;
    private String unit;
    private String result;
    private String way;
    private Integer score;
    private String arg;
    private String process;
    private BaseInfo dept;
    private Standard standard;


    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "unit", nullable = true, insertable = true, updatable = true, length = 255)
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Basic
    @Column(name = "result", nullable = true, insertable = true, updatable = true, length = 255)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Basic
    @Column(name = "way", nullable = true, insertable = true, updatable = true, length = 255)
    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "ach_type", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAchType() {
        return achType;
    }

    public void setAchType(String achType) {
        this.achType = achType;
    }

    @Basic
    @Column(name = "arg", nullable = true, insertable = true, updatable = true, length = 255)
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Transient
    public Map getArgMap() {
        if (this.arg == null) {
            return new HashMap();
        }
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

    public void setArgMap(Map argMap) {
        try {
            this.arg = new ObjectMapper().writeValueAsString(argMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Basic
    @Column(name = "process", nullable = true, insertable = true, updatable = true, length = 255)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AchAward achAward = (AchAward) o;

        if (id != null ? !id.equals(achAward.id) : achAward.id != null) return false;
        if (name != null ? !name.equals(achAward.name) : achAward.name != null) return false;
        if (date != null ? !date.equals(achAward.date) : achAward.date != null) return false;
        if (unit != null ? !unit.equals(achAward.unit) : achAward.unit != null) return false;
        if (result != null ? !result.equals(achAward.result) : achAward.result != null) return false;
        if (way != null ? !way.equals(achAward.way) : achAward.way != null) return false;
        if (score != null ? !score.equals(achAward.score) : achAward.score != null) return false;
        if (arg != null ? !arg.equals(achAward.arg) : achAward.arg != null) return false;
        if (process != null ? !process.equals(achAward.process) : achAward.process != null) return false;
        if (dept != null ? !dept.equals(achAward.dept) : achAward.dept != null) return false;
        return !(standard != null ? !standard.equals(achAward.standard) : achAward.standard != null);

    }

    @Override
    public int hashCode() {
        int result1 = id != null ? id.hashCode() : 0;
        result1 = 31 * result1 + (name != null ? name.hashCode() : 0);
        result1 = 31 * result1 + (date != null ? date.hashCode() : 0);
        result1 = 31 * result1 + (unit != null ? unit.hashCode() : 0);
        result1 = 31 * result1 + (result != null ? result.hashCode() : 0);
        result1 = 31 * result1 + (way != null ? way.hashCode() : 0);
        result1 = 31 * result1 + (score != null ? score.hashCode() : 0);
        result1 = 31 * result1 + (arg != null ? arg.hashCode() : 0);
        result1 = 31 * result1 + (process != null ? process.hashCode() : 0);
        result1 = 31 * result1 + (dept != null ? dept.hashCode() : 0);
        result1 = 31 * result1 + (standard != null ? standard.hashCode() : 0);
        return result1;
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
