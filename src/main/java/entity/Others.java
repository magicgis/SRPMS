package entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Map;

import static util.Trans.argMap;

/**
 * Created by guofan on 2015/11/19.
 */
@Entity
@Table(name = "others")
public class Others implements VirtualEntity {
    private String id;
    private String name;
    private String productionNo;
    private String date;
    private Integer score;
    private String process;
    private String arg;

    private BaseInfo dept;
    private Standard standard;

    @Id
    @Column(name = "id", nullable = false, length = 255)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "production_no", nullable = true, length = 255)
    public String getProductionNo() {
        return productionNo;
    }

    public void setProductionNo(String productionNo) {
        this.productionNo = productionNo;
    }


    @Basic
    @Column(name = "date", nullable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "process", nullable = true, length = 255)
    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    @Basic
    @Column(name = "arg", nullable = true, length = 3000)
    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Others others = (Others) o;

        if (id != null ? !id.equals(others.id) : others.id != null) return false;
        if (name != null ? !name.equals(others.name) : others.name != null) return false;
        if (productionNo != null ? !productionNo.equals(others.productionNo) : others.productionNo != null)
            return false;
        if (date != null ? !date.equals(others.date) : others.date != null) return false;
        if (score != null ? !score.equals(others.score) : others.score != null) return false;
        if (process != null ? !process.equals(others.process) : others.process != null) return false;
        if (arg != null ? !arg.equals(others.arg) : others.arg != null) return false;
        if (dept != null ? !dept.equals(others.dept) : others.dept != null) return false;
        return !(standard != null ? !standard.equals(others.standard) : others.standard != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (productionNo != null ? productionNo.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (process != null ? process.hashCode() : 0);
        result = 31 * result + (arg != null ? arg.hashCode() : 0);
        result = 31 * result + (dept != null ? dept.hashCode() : 0);
        result = 31 * result + (standard != null ? standard.hashCode() : 0);
        return result;
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
            } catch (Exception x) {
                x.printStackTrace();
            }
            if (map != null) {
                argMap.put("View", map);
            }
            this.arg = new ObjectMapper().writeValueAsString(argMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
