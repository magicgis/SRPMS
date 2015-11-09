package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;

import static util.Trans.argMap;

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
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal value;
    private BigDecimal value2;
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
    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    @Basic
    @Column(name = "min")
    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    @Basic
    @Column(name = "value")
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Basic
    @Column(name = "value2")
    public BigDecimal getValue2() {
        return value2;
    }

    public void setValue2(BigDecimal value2) {
        this.value2 = value2;
    }

    @Transient
    public Map getInfoMap() {
        return argMap(getInfo());
    }

    public void setInfoMap(Map infoMap) {
        try {
            this.info = new ObjectMapper().writeValueAsString(info);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
        if (value != null ? !value.equals(standard.value) : standard.value != null) return false;
        if (value2 != null ? !value2.equals(standard.value2) : standard.value2 != null) return false;
        return !(infoMap != null ? !infoMap.equals(standard.infoMap) : standard.infoMap != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (min != null ? min.hashCode() : 0);
        result = 31 * result + (max != null ? max.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (value2 != null ? value2.hashCode() : 0);
        result = 31 * result + (infoMap != null ? infoMap.hashCode() : 0);
        return result;
    }
}
