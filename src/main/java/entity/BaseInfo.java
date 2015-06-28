package entity;

import javax.persistence.*;

/**
 * Created by guofan on 2015/5/6.
 */
@Entity
@Table(name = "base_info")
public class BaseInfo {
    private String id;
    private String tableName;
    private String keyCode;
    private String value;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "table_name")
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "key_code")
    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    @Basic
    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseInfo baseInfo = (BaseInfo) o;

        if (id != null ? !id.equals(baseInfo.id) : baseInfo.id != null) return false;
        if (tableName != null ? !tableName.equals(baseInfo.tableName) : baseInfo.tableName != null) return false;
        if (keyCode != null ? !keyCode.equals(baseInfo.keyCode) : baseInfo.keyCode != null) return false;
        if (value != null ? !value.equals(baseInfo.value) : baseInfo.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (keyCode != null ? keyCode.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
