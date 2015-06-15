package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/6/10.
 */
@Entity
@Table(name = "standard", schema = "", catalog = "srpms")
public class Standard {
    private String id;
    private String type;
    private String info;
    private Integer maxNum;
    private int value;

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
    @Column(name = "max_num")
    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    @Basic
    @Column(name = "value")
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Standard standard = (Standard) o;

        if (value != standard.value) return false;
        if (id != null ? !id.equals(standard.id) : standard.id != null) return false;
        if (type != null ? !type.equals(standard.type) : standard.type != null) return false;
        if (info != null ? !info.equals(standard.info) : standard.info != null) return false;
        if (maxNum != null ? !maxNum.equals(standard.maxNum) : standard.maxNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (maxNum != null ? maxNum.hashCode() : 0);
        result = 31 * result + value;
        return result;
    }
}
