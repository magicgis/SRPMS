package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Map;

import static util.Trans.str2Map;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "standard")
public class Standard {
    private String id;
    private String type;
    @JsonIgnore
    private String info;
    private Integer min;
    private Integer max;
    private Integer value;
    private Map infoMap;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "max")
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    @Basic
    @Column(name = "min")
    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    @Basic
    @Column(name = "value")
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Transient
    public Map getInfoMap() {
        return str2Map(getInfo());
    }

    public void setInfoMap(Map infoMap) {
        this.info = infoMap.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Standard standard = (Standard) o;

        if (id != null ? !id.equals(standard.id) : standard.id != null) return false;
        if (type != null ? !type.equals(standard.type) : standard.type != null) return false;
        if (info != null ? !info.equals(standard.info) : standard.info != null) return false;
        if (min != null ? !min.equals(standard.min) : standard.min != null) return false;
        if (max != null ? !max.equals(standard.max) : standard.max != null) return false;
        return !(value != null ? !value.equals(standard.value) : standard.value != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (min != null ? min.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
